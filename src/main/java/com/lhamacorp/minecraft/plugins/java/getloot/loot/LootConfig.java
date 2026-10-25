package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Everything read from {@code config.yml}. Invalid entries are skipped with a warning
 * instead of failing the plugin.
 */
public final class LootConfig {

    private static final int DEFAULT_CUSTOM_THRESHOLD = 900;
    private static final int DEFAULT_MAX_STACKS = 3;

    private final Map<EntityType, List<DropRule>> mobs = new EnumMap<>(EntityType.class);
    private final Map<String, Tier> tiers = new HashMap<>();
    private final List<DropRule> bonusDrops = new ArrayList<>();
    private final List<ExchangeRecipe> recipes = new ArrayList<>();
    private int maxExchangeStacks = DEFAULT_MAX_STACKS;

    private final Logger log;
    private final Function<NamespacedKey, Enchantment> enchantmentLookup;

    private LootConfig(Logger log, Function<NamespacedKey, Enchantment> enchantmentLookup) {
        this.log = log;
        this.enchantmentLookup = enchantmentLookup;
    }

    /**
     * @param enchantmentLookup resolves enchantment keys, normally {@code Registry.ENCHANTMENT::get}
     */
    public static LootConfig load(ConfigurationSection config,
                                  Logger log,
                                  Function<NamespacedKey, Enchantment> enchantmentLookup) {
        LootConfig loot = new LootConfig(log, enchantmentLookup);
        loot.readTiers(config.getConfigurationSection("tiers"));
        loot.bonusDrops.addAll(loot.readDrops(config.getMapList("bonus-drops"), "bonus-drops"));
        loot.readExchange(config.getConfigurationSection("exchange"));
        loot.readMobs(config.getConfigurationSection("mobs"));
        return loot;
    }

    public List<DropRule> dropsFor(EntityType type) {
        return mobs.getOrDefault(type, List.of());
    }

    public List<DropRule> bonusDrops() {
        return bonusDrops;
    }

    public int maxExchangeStacks() {
        return maxExchangeStacks;
    }

    public Optional<Tier> exchangeRewardFor(Set<Material> inputs) {
        return recipes.stream()
                .filter(recipe -> recipe.inputs().equals(inputs))
                .map(ExchangeRecipe::reward)
                .findFirst();
    }

    private void readTiers(ConfigurationSection section) {
        if (section == null) return;

        for (String name : section.getKeys(false)) {
            ConfigurationSection tier = section.getConfigurationSection(name);
            if (tier == null) continue;

            List<Material> items = materials(tier.getStringList("items"), "tiers." + name);
            if (items.isEmpty()) {
                log.warning("Tier " + name + " has no valid items, ignoring it");
                continue;
            }
            tiers.put(name.toUpperCase(Locale.ROOT), new Tier(name, tier.getInt("threshold", DEFAULT_CUSTOM_THRESHOLD), items));
        }
    }

    private void readExchange(ConfigurationSection section) {
        if (section == null) return;

        maxExchangeStacks = section.getInt("max-stacks", DEFAULT_MAX_STACKS);
        for (Map<?, ?> raw : section.getMapList("recipes")) {
            Tier reward = tiers.get(String.valueOf(raw.get("tier")).toUpperCase(Locale.ROOT));
            Set<Material> inputs = new HashSet<>(materials(strings(raw.get("inputs")), "exchange.recipes"));
            if (reward == null || inputs.isEmpty()) {
                log.warning("Skipping exchange recipe with unknown tier or inputs: " + raw);
                continue;
            }
            recipes.add(new ExchangeRecipe(inputs, reward));
        }
    }

    private void readMobs(ConfigurationSection section) {
        if (section == null) return;

        for (String key : section.getKeys(false)) {
            ConfigurationSection mob = section.getConfigurationSection(key);
            if (mob == null) continue;

            List<String> names = mob.contains("entities") ? mob.getStringList("entities") : List.of(key);
            List<DropRule> drops = readDrops(mob.getMapList("drops"), "mobs." + key);

            for (String name : names) {
                try {
                    mobs.put(EntityType.valueOf(name.toUpperCase(Locale.ROOT)), drops);
                } catch (IllegalArgumentException e) {
                    log.warning("Unknown entity type '" + name + "' in mobs." + key + ", skipping (server too old?)");
                }
            }
        }
    }

    private List<DropRule> readDrops(List<Map<?, ?>> raw, String path) {
        List<DropRule> rules = new ArrayList<>();

        for (Map<?, ?> entry : raw) {
            List<Material> items;
            int threshold = number(entry.get("threshold"), DEFAULT_CUSTOM_THRESHOLD).intValue();

            if (entry.containsKey("tier")) {
                Tier tier = tiers.get(String.valueOf(entry.get("tier")).toUpperCase(Locale.ROOT));
                if (tier == null) {
                    log.warning("Unknown tier '" + entry.get("tier") + "' in " + path);
                    continue;
                }
                items = tier.items();
                threshold = tier.threshold();
            } else {
                items = materials(strings(entry.get("items")), path);
            }

            if (items.isEmpty()) continue;

            rules.add(new DropRule(
                    items,
                    threshold,
                    number(entry.get("tries"), 1).intValue(),
                    number(entry.get("multiplier"), 1.0).doubleValue(),
                    enchantments(strings(entry.get("enchantments")), path)));
        }

        return rules;
    }

    private List<Material> materials(List<String> names, String path) {
        List<Material> materials = new ArrayList<>();
        for (String name : names) {
            Material material = Material.matchMaterial(name);
            if (material == null) {
                log.warning("Unknown material '" + name + "' in " + path + ", skipping (server too old?)");
            } else {
                materials.add(material);
            }
        }
        return materials;
    }

    private List<Enchantment> enchantments(List<String> names, String path) {
        List<Enchantment> enchantments = new ArrayList<>();
        for (String name : names) {
            NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            Enchantment enchantment = key == null ? null : enchantmentLookup.apply(key);
            if (enchantment == null) {
                log.warning("Unknown enchantment '" + name + "' in " + path + ", skipping");
            } else {
                enchantments.add(enchantment);
            }
        }
        return enchantments;
    }

    private static List<String> strings(Object value) {
        List<String> strings = new ArrayList<>();
        if (value instanceof List<?> list) {
            list.forEach(element -> strings.add(String.valueOf(element)));
        }
        return strings;
    }

    private static Number number(Object value, Number fallback) {
        return value instanceof Number number ? number : fallback;
    }
}

package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LootConfigTest {

    private static final String YAML = String.join("\n",
            "tiers:",
            "  RARE: { threshold: 990, items: [DIAMOND, NOT_A_MATERIAL] }",
            "bonus-drops:",
            "  - { tier: RARE }",
            "exchange:",
            "  max-stacks: 2",
            "  recipes:",
            "    - { inputs: [GOLD_INGOT, IRON_INGOT], tier: RARE }",
            "    - { inputs: [DIRT], tier: MISSING }",
            "mobs:",
            "  zombie:",
            "    drops:",
            "      - { tier: RARE, tries: 3, multiplier: 1.5 }",
            "      - { items: [APPLE], tries: 2 }",
            "      - { tier: MISSING }",
            "  pillager:",
            "    entities: [PILLAGER, VINDICATOR, NOT_AN_ENTITY]",
            "    drops:",
            "      - { items: [BOW] }");

    private static LootConfig load(Reader reader, List<String> warnings) {
        Logger log = Logger.getAnonymousLogger();
        log.setUseParentHandlers(false);
        log.addHandler(new java.util.logging.Handler() {
            @Override
            public void publish(java.util.logging.LogRecord record) {
                warnings.add(record.getMessage());
            }

            @Override
            public void flush() {
            }

            @Override
            public void close() {
            }
        });
        return LootConfig.load(YamlConfiguration.loadConfiguration(reader), log, key -> null);
    }

    @Test
    public void readsTierAndCustomDrops() {
        LootConfig config = load(new StringReader(YAML), new ArrayList<>());

        List<DropRule> zombie = config.dropsFor(EntityType.ZOMBIE);
        assertEquals(2, zombie.size());
        assertEquals(List.of(Material.DIAMOND), zombie.get(0).items());
        assertEquals(990, zombie.get(0).threshold());
        assertEquals(3, zombie.get(0).tries());
        assertEquals(1.5, zombie.get(0).multiplier(), 0);
        assertEquals(900, zombie.get(1).threshold());
    }

    @Test
    public void oneEntryCanCoverSeveralEntities() {
        LootConfig config = load(new StringReader(YAML), new ArrayList<>());

        assertEquals(1, config.dropsFor(EntityType.PILLAGER).size());
        assertEquals(1, config.dropsFor(EntityType.VINDICATOR).size());
        assertTrue(config.dropsFor(EntityType.COW).isEmpty());
    }

    @Test
    public void readsExchangeRecipes() {
        LootConfig config = load(new StringReader(YAML), new ArrayList<>());

        assertEquals(2, config.maxExchangeStacks());
        assertTrue(config.exchangeRewardFor(Set.of(Material.GOLD_INGOT, Material.IRON_INGOT)).isPresent());
        assertFalse(config.exchangeRewardFor(Set.of(Material.GOLD_INGOT)).isPresent());
        assertFalse(config.exchangeRewardFor(Set.of(Material.DIRT)).isPresent());
        assertEquals(1, config.bonusDrops().size());
    }

    @Test
    public void invalidEntriesAreSkippedWithAWarning() {
        List<String> warnings = new ArrayList<>();
        load(new StringReader(YAML), warnings);

        assertEquals(4, warnings.size());
    }

    @Test
    public void shippedConfigIsValid() {
        List<String> warnings = new ArrayList<>();
        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/config.yml"));

        LootConfig config = load(reader, warnings);

        // the enchantment lookup is stubbed out here, so only those warnings are expected
        warnings.removeIf(warning -> warning.startsWith("Unknown enchantment"));
        assertEquals(List.of(), warnings);
        assertFalse(config.dropsFor(EntityType.ZOMBIE).isEmpty());
        assertTrue(config.exchangeRewardFor(Set.of(Material.EMERALD, Material.LAPIS_LAZULI)).isPresent());
    }
}

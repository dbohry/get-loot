package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;
import static org.bukkit.Material.*;

public class GetLootFromKill implements Listener {

    private final LootHelper helper = new LootHelper();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity creature = event.getEntity();

        switch (creature.getType()) {
            case VILLAGER:
            case ZOMBIE_VILLAGER:
                spawnItems(creature, prepareVillagerLoot());
                break;
            case PILLAGER:
                spawnItems(creature, preparePillagerLoot());
                break;
            case ZOMBIE:
            case DROWNED:
                spawnItems(creature, prepareZombieLoot());
                break;
            case SKELETON:
                spawnItems(creature, prepareSkeletonLoot());
                break;
            case CREEPER:
                spawnItems(creature, prepareCreeperLoot());
                break;
            case SPIDER:
                spawnItems(creature, prepareSpiderLoot());
                break;
            case WITCH:
                spawnItems(creature, prepareWitchLoot());
                break;
            case ENDERMAN:
                spawnItems(creature, prepareEndermanLoot());
                break;
            case CHICKEN:
                spawnItems(creature, prepareChickenLoot());
                break;
            case PIG:
                spawnItems(creature, preparePigLoot());
                break;
            case COW:
                spawnItems(creature, prepareCowLoot());
                break;
            case SHEEP:
                spawnItems(creature, prepareSheepLoot());
                break;
        }
    }

    private List<ItemStack> prepareVillagerLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.08f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.05f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.08f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.05f));

        return loot;
    }

    private List<ItemStack> preparePillagerLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 3, 1.08f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.05f));
        loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.01f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.08f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.05f));

        List<Material> customItems = Arrays.asList(
                ARROW,
                SPECTRAL_ARROW,
                TIPPED_ARROW,
                CROSSBOW,
                BOW
        );

        loot.addAll(helper.createLoot(Rarity.CUSTOM, 1, 1, customItems));

        return loot;
    }

    private List<ItemStack> prepareZombieLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1));

        List<Material> customItems = Arrays.asList(
                MELON_SEEDS,
                WHEAT_SEEDS,
                PUMPKIN_SEEDS,
                APPLE,
                POTATO,
                CARROT,
                SWEET_BERRIES,
                BEETROOT,
                BEETROOT_SEEDS
        );

        loot.addAll(helper.createLoot(Rarity.CUSTOM, 1, 1, customItems));

        return loot;
    }

    private List<ItemStack> prepareSpiderLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.010f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1));

        List<Material> customItems = Arrays.asList(
                SPIDER_SPAWN_EGG,
                COBWEB
        );

        loot.addAll(helper.createLoot(Rarity.CUSTOM, 1, 1, customItems));

        return loot;
    }

    private List<ItemStack> prepareSkeletonLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.025f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.015f));

        List<Material> customItems = Arrays.asList(
                ARROW,
                SPECTRAL_ARROW,
                TIPPED_ARROW,
                CROSSBOW,
                BOW
        );

        loot.addAll(helper.createLoot(Rarity.CUSTOM, 1, 1, customItems));

        return loot;
    }

    private List<ItemStack> prepareCreeperLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.030f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.020f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.30f));

        return loot;
    }

    private List<ItemStack> prepareEndermanLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 3, 1.040f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 3, 1.025f));
        loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.01f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.040f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.025f));

        return loot;
    }

    private List<ItemStack> prepareWitchLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 3, 1.045f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 3, 1.030f));
        loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.01f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.045f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.030f));

        List<Material> customItems = Arrays.asList(
                WOODEN_SWORD,
                STONE_SWORD,
                IRON_SWORD,
                DIAMOND_SWORD
        );

        Collections.shuffle(customItems);
        List<ItemStack> customLoot = helper.createLoot(Rarity.CUSTOM, 1, 1, customItems);

        List<Enchantment> enchantments = Arrays.asList(
                Enchantment.FIRE_ASPECT,
                Enchantment.DAMAGE_ALL
        );

        customLoot.forEach(item -> {
            Collections.shuffle(enchantments);
            if (enchantments.stream().findFirst().isPresent()) {
                item.addEnchantment(enchantments.stream().findFirst().get(), 1);
            } else {
                System.out.println("impossible to get item");
            }
        });

        loot.addAll(customLoot);

        return loot;
    }

    private List<ItemStack> prepareChickenLoot() {
        List<Material> customLoot = Collections.singletonList(Material.CHICKEN_SPAWN_EGG);
        return new ArrayList<>(helper.createLoot(Rarity.CUSTOM, 1, 1, customLoot));
    }

    private List<ItemStack> preparePigLoot() {
        List<Material> customLoot = Collections.singletonList(Material.PIG_SPAWN_EGG);
        return new ArrayList<>(helper.createLoot(Rarity.CUSTOM, 1, 1, customLoot));
    }

    private List<ItemStack> prepareCowLoot() {
        List<Material> customLoot = Collections.singletonList(Material.COW_SPAWN_EGG);
        return new ArrayList<>(helper.createLoot(Rarity.CUSTOM, 1, 1, customLoot));
    }

    private List<ItemStack> prepareSheepLoot() {
        List<Material> customLoot = Collections.singletonList(Material.SHEEP_SPAWN_EGG);
        return new ArrayList<>(helper.createLoot(Rarity.CUSTOM, 1, 1, customLoot));
    }

    private List<ItemStack> addCurrencyItem() {
        List<ItemStack> loot = new ArrayList<>();
        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1));

        return loot;
    }

    private void spawnItems(LivingEntity entity, List<ItemStack> items) {
        if (items.isEmpty()) return;

        items.addAll(addCurrencyItem());

        for (ItemStack item : items) {
            entity
                    .getWorld()
                    .dropItemNaturally(entity.getLocation(), item);
        }
    }

}

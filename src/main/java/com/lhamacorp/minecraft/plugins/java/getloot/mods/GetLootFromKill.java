package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class GetLootFromKill implements Listener {

    private final LootHelper helper = new LootHelper();

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity enemy = event.getEntity();

        if (enemy.getType() == EntityType.VILLAGER) {
            spawnItems(enemy, prepareVillageLoot());
        }

        if (enemy.getType() == EntityType.ZOMBIE) {
            spawnItems(enemy, prepareZombieLoot());
        }

        if (enemy.getType() == EntityType.SKELETON) {
            spawnItems(enemy, prepareSkeletonLoot());
        }

        if (enemy.getType() == EntityType.CREEPER) {
            spawnItems(enemy, prepareCreeperLoot());
        }

        if (enemy.getType() == EntityType.SPIDER) {
            spawnItems(enemy, prepareSpiderLoot());
        }

        if (enemy.getType() == EntityType.WITCH) {
            spawnItems(enemy, prepareWitchLoot());
        }

        if (enemy.getType() == EntityType.ENDERMAN) {
            spawnItems(enemy, prepareEndermanLoot());
        }

        if (enemy.getType() == EntityType.CHICKEN) {
            spawnItems(enemy, prepareChickenLoot());
        }

        if (enemy.getType() == EntityType.SHEEP) {
            spawnItems(enemy, prepareSheepLoot());
        }

    }

    private List<ItemStack> prepareVillageLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1.08f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.05f));

        return loot;
    }

    private List<ItemStack> prepareZombieLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1));

        return loot;
    }

    private List<ItemStack> prepareSpiderLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1));

        return loot;
    }

    private List<ItemStack> prepareSkeletonLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1.025f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.015f));

        return loot;
    }

    private List<ItemStack> prepareCreeperLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1.030f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.020f));

        return loot;
    }

    private List<ItemStack> prepareEndermanLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1.040f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.025f));

        return loot;
    }

    private List<ItemStack> prepareWitchLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 1, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1.045f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.030f));

        return loot;
    }

    private List<ItemStack> prepareChickenLoot() {
        return Collections.emptyList();
    }

    private List<ItemStack> prepareSheepLoot() {
        return Collections.emptyList();
    }

    private void spawnItems(LivingEntity entity, List<ItemStack> items) {
        if (items.isEmpty()) return;
        for (ItemStack item : items) {
            entity
                    .getWorld()
                    .dropItemNaturally(entity.getLocation(), item);
        }
    }

}

package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
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

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity enemy = event.getEntity();

        if (enemy.getType() == EntityType.VILLAGER || enemy.getType() == EntityType.ZOMBIE) {
            spawnItems(enemy, prepareVillageLoot());
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

        if (shouldDrop(random(), Rarity.RAREST))
            loot.add(new ItemStack(Material.EMERALD, 1));

        if (shouldDrop(random(), Rarity.RAREST))
            loot.add(new ItemStack(Material.GOLD_NUGGET, random(2, 1)));

        if (shouldDrop(random(), Rarity.RARE))
            loot.add(new ItemStack(Material.IRON_NUGGET, random(3, 1)));

        if (shouldDrop(random(), Rarity.COMMON))
            loot.add(new ItemStack(Material.IRON_SWORD, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.WOODEN_AXE, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.WOODEN_PICKAXE, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.WOODEN_SHOVEL, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.LEATHER_BOOTS, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));

        if (shouldDrop(random(), Rarity.VERY_COMMON))
            loot.add(new ItemStack(Material.LEATHER_HELMET, 1));

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

    private boolean shouldDrop(int value, Rarity rarity) {
        return value > rarity.getRarity();
    }

}

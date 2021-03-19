package com.lhamacorp.minecraft.plugins.java.getloot.mod;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GetLoot implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity enemy = event.getEntity();

        if (enemy.getType() == EntityType.CHICKEN) {

            List<ItemStack> loot = new ArrayList<>();

            if (shouldDrop(random(), Rarity.RAREST))
                loot.add(new ItemStack(Material.DIAMOND, 1));

            if (shouldDrop(random(), Rarity.RARE))
                loot.add(new ItemStack(Material.GOLD_NUGGET, random()));

            if (shouldDrop(random(), Rarity.COMMON))
                loot.add(new ItemStack(Material.IRON_SWORD, 1));

            spawnItems(enemy, loot);
        }

    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.COBBLESTONE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);

            List<ItemStack> loot = new ArrayList<>();

            if (shouldDrop(random(), Rarity.RAREST))
                loot.add(new ItemStack(Material.DIAMOND, 1));

            if (shouldDrop(random(), Rarity.RAREST))
                loot.add(new ItemStack(Material.GOLD_INGOT, 1));

            spawnItems(blockBroken, loot);
        }

    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        Block formedBlock = event.getNewState().getBlock();

        if (formedBlock.getType() == Material.LAVA) {
            event.setCancelled(true);
            formedBlock.setType(Material.AIR);
            formedBlock.setType(Material.IRON_ORE);
        }
    }

    private void spawnItems(Block blockBroken, List<ItemStack> items) {
        for (ItemStack item : items) {
            blockBroken
                    .getWorld()
                    .dropItemNaturally(blockBroken.getLocation(), item);
        }
    }

    private void spawnItems(LivingEntity blockBroken, List<ItemStack> items) {
        for (ItemStack item : items) {
            blockBroken
                    .getWorld()
                    .dropItemNaturally(blockBroken.getLocation(), item);
        }
    }

    private Integer random() {
        return ThreadLocalRandom.current().nextInt(1, 10 + 1);
    }

    private boolean shouldDrop(int value, Rarity rarity) {
        return value > rarity.getRarity();
    }

}

package com.lhamacorp.minecraft.plugins.java.getloot;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GetLoot implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.COBBLESTONE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);

            ItemStack diamonds = new ItemStack(Material.DIAMOND, random());
            ItemStack gold = new ItemStack(Material.GOLD_INGOT, random());

            spawnItems(blockBroken, Arrays.asList(diamonds, gold));
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

    private Integer random() {
        return ThreadLocalRandom.current().nextInt(1, 3 + 1);
    }

}

package com.lhamacorp.minecraft.plugins.java.getloot;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakIntoSmallPart implements Listener {

    private static final int AMOUNT = 9;

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Block brokenBlock = event.getBlock();

        if (brokenBlock.getType() == Material.GOLD_BLOCK) {
            event.setCancelled(true);
            breakBlockIntoParts(brokenBlock, Material.GOLD_INGOT);
        }

        if (brokenBlock.getType() == Material.IRON_BLOCK) {
            event.setCancelled(true);
            breakBlockIntoParts(brokenBlock, Material.IRON_INGOT);
        }

        if (brokenBlock.getType() == Material.DIAMOND_BLOCK) {
            event.setCancelled(true);
            breakBlockIntoParts(brokenBlock, Material.DIAMOND);
        }

    }

    private void breakBlockIntoParts(Block block, Material material) {
        block.setType(Material.AIR);
        ItemStack bars = new ItemStack(material, BreakIntoSmallPart.AMOUNT);
        block
                .getWorld()
                .dropItemNaturally(block.getLocation(), bars);
    }

}

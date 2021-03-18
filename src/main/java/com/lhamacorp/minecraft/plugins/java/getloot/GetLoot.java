package com.lhamacorp.minecraft.plugins.java.getloot;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class GetLoot implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.COBBLESTONE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack redstone = new ItemStack(Material.DIAMOND, 100);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), redstone);
        }

    }

}

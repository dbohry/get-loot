package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class BlockTransform implements Listener  {

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        Block formedBlock = event.getNewState().getBlock();

        if (formedBlock.getType() == Material.LAVA) {
            event.setCancelled(true);
            formedBlock.setType(Material.AIR);
            formedBlock.setType(Material.IRON_ORE);
        }
    }

}

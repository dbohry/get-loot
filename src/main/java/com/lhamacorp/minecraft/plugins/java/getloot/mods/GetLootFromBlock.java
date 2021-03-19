package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class GetLootFromBlock implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.COBBLESTONE) {
            event.setCancelled(true);
            blockBroken.setType(Material.AIR);

            List<ItemStack> loot = new ArrayList<>();

            if (shouldDrop(random(), Rarity.VERY_RARE))
                loot.add(new ItemStack(Material.DIAMOND, 1));

            if (shouldDrop(random(), Rarity.RARE))
                loot.add(new ItemStack(Material.GOLD_INGOT, 1));

            spawnItems(blockBroken, loot);
        }

    }

    private void spawnItems(Block blockBroken, List<ItemStack> items) {
        for (ItemStack item : items) {
            blockBroken
                    .getWorld()
                    .dropItemNaturally(blockBroken.getLocation(), item);
        }
    }

    private boolean shouldDrop(int value, Rarity rarity) {
        return value > rarity.getRarity();
    }

}

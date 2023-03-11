package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ExchangeCurrency implements Listener {

    private final LootHelper lootHelper = new LootHelper();

    @EventHandler
    public void onCloseChest(InventoryCloseEvent event) {
        if (event.getInventory().getType().equals(InventoryType.CHEST)) {
            try {
                List<ItemStack> chestItems = Arrays.stream(event.getInventory().getContents())
                        .filter(Objects::nonNull)
                        .toList();

                Block blockBelowChest = event.getInventory().getLocation().getBlock().getRelative(BlockFace.DOWN);
                if (chestItems.size() > 3 || blockBelowChest.getType() != Material.REDSTONE_BLOCK) {
                    return;
                }

                Rarity rarity = defineRarityScore(chestItems.stream().map(ItemStack::getType).collect(toSet()));

                if (rarity == null) {
                    return;
                }

                chestItems.forEach(item -> {
                    event.getPlayer().getInventory().addItem(new ItemStack(item.getType(), item.getAmount() - 1));
                    event.getInventory().remove(item);
                });

                lootHelper.createLoot(rarity, 1, 1);
                event.getPlayer().getInventory().addItem(lootHelper.createLoot(rarity));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Rarity defineRarityScore(Set<Material> materials) {
        if (materials.containsAll(List.of(Material.EMERALD, Material.LAPIS_LAZULI))) {
            return Rarity.EPIC;
        }

        return null;
    }


}

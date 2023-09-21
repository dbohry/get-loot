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
import static org.bukkit.Material.*;

public class ExchangeCurrency implements Listener {

    private final LootHelper lootHelper = new LootHelper();

    private static final int ZERO = 0;

    @EventHandler
    public void onCloseChest(InventoryCloseEvent event) {
        if (event.getInventory().getType().equals(InventoryType.CHEST)) {
            try {
                Block blockBelowChest = getTypeBlockBelowChest(event);

                if (blockBelowChest.getType() != Material.REDSTONE_BLOCK) {
                    return;
                }

                List<ItemStack> chestItems = getItemsFromChest(event);

                if (chestItems.size() > 3) {
                    return;
                }

                int rarityScore = calculateRarityScore(chestItems.stream()
                        .map(ItemStack::getType)
                        .collect(toSet()));

                if (rarityScore == ZERO) {
                    return;
                }

                exchangeChestItems(event, chestItems, rarityScore);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int calculateRarityScore(Set<Material> materials) {
        if (materials.containsAll(List.of(EMERALD, LAPIS_LAZULI, GOLD_INGOT))) {
            return 1000;
        }

        if (materials.containsAll(List.of(EMERALD, LAPIS_LAZULI))) {
            return 999;
        }

        if (materials.containsAll(List.of(GOLD_INGOT, IRON_INGOT))) {
            return 990;
        }

        if (materials.containsAll(List.of(GOLD_NUGGET, IRON_NUGGET))) {
            return 950;
        }

        return ZERO;
    }

    private void exchangeChestItems(InventoryCloseEvent event, List<ItemStack> chestItems, int rarityScore) {
        chestItems.forEach(item -> {
            event.getPlayer().getInventory().addItem(new ItemStack(item.getType(), item.getAmount() - 1));
            event.getInventory().remove(item);
        });

        event.getPlayer().getInventory().addItem(lootHelper.createLoot(Rarity.rarityFromScore(rarityScore)));
    }

    private static List<ItemStack> getItemsFromChest(InventoryCloseEvent event) {
        return Arrays.stream(event.getInventory().getContents())
                .filter(Objects::nonNull)
                .toList();
    }

    private static Block getTypeBlockBelowChest(InventoryCloseEvent event) {
        return Objects.requireNonNull(event.getInventory().getLocation())
                .getBlock()
                .getRelative(BlockFace.DOWN);
    }

}

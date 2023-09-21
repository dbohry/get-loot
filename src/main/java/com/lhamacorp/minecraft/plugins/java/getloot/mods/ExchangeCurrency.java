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

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class ExchangeCurrency implements Listener {

    private final LootHelper lootHelper = new LootHelper();

    private static final int ZERO = 0;
    private static final int MAX_RARITY_SCORE = 1000;
    private static final int HIGH_RARITY_SCORE = 999;
    private static final int MEDIUM_RARITY_SCORE = 990;
    private static final int LOW_RARITY_SCORE = 950;

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

                int rarityScore = calculateRarityScoreFromMaterials(chestItems.stream()
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

    public int calculateRarityScoreFromMaterials(Set<Material> materials) {
        Map<Set<Material>, Integer> rarityMap = Map.of(
                Set.of(Material.EMERALD, Material.LAPIS_LAZULI, Material.GOLD_INGOT), MAX_RARITY_SCORE,
                Set.of(Material.EMERALD, Material.LAPIS_LAZULI), HIGH_RARITY_SCORE,
                Set.of(Material.GOLD_INGOT, Material.IRON_INGOT), MEDIUM_RARITY_SCORE,
                Set.of(Material.GOLD_NUGGET, Material.IRON_NUGGET), LOW_RARITY_SCORE
        );

        return rarityMap.getOrDefault(materials, ZERO);
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

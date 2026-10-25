package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootConfig;
import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootRoller;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Closing a chest that sits on a redstone block, holding exactly the items of an exchange
 * recipe, consumes all but one of each stack and rewards one item of the recipe's tier.
 */
public class ExchangeCurrency implements Listener {

    private final LootConfig config;
    private final LootRoller roller;

    public ExchangeCurrency(LootConfig config, LootRoller roller) {
        this.config = config;
        this.roller = roller;
    }

    @EventHandler
    public void onCloseChest(InventoryCloseEvent event) {
        Inventory chest = event.getInventory();
        Location location = chest.getLocation();

        if (chest.getType() != InventoryType.CHEST || location == null) return;
        if (location.getBlock().getRelative(BlockFace.DOWN).getType() != Material.REDSTONE_BLOCK) return;

        List<ItemStack> stacks = Arrays.stream(chest.getContents())
                .filter(Objects::nonNull)
                .toList();
        if (stacks.isEmpty() || stacks.size() > config.maxExchangeStacks()) return;

        Set<Material> inputs = stacks.stream().map(ItemStack::getType).collect(Collectors.toSet());

        config.exchangeRewardFor(inputs).ifPresent(reward -> {
            chest.clear();

            HumanEntity player = event.getPlayer();
            for (ItemStack stack : stacks) {
                give(player, stack, stack.getAmount() - 1);
            }
            give(player, roller.pick(reward), 1);
        });
    }

    private static void give(HumanEntity player, ItemStack template, int amount) {
        if (amount <= 0) return;

        ItemStack item = template.clone();
        item.setAmount(amount);
        player.getInventory().addItem(item)
                .values()
                .forEach(leftover -> player.getWorld().dropItemNaturally(player.getLocation(), leftover));
    }

}

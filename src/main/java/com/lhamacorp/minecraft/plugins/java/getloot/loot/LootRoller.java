package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootRoller {

    private final Random random;

    public LootRoller(Random random) {
        this.random = random;
    }

    public List<ItemStack> roll(List<DropRule> rules) {
        List<ItemStack> loot = new ArrayList<>();
        for (DropRule rule : rules) {
            if (random.nextDouble() < rule.chance()) {
                loot.add(create(rule));
            }
        }
        return loot;
    }

    public ItemStack pick(Tier tier) {
        return new ItemStack(tier.items().get(random.nextInt(tier.items().size())));
    }

    private ItemStack create(DropRule rule) {
        ItemStack item = new ItemStack(rule.items().get(random.nextInt(rule.items().size())));
        if (!rule.enchantments().isEmpty()) {
            item.addUnsafeEnchantment(rule.enchantments().get(random.nextInt(rule.enchantments().size())), 1);
        }
        return item;
    }
}

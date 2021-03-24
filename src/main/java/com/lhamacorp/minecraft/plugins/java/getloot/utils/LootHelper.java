package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static com.lhamacorp.minecraft.plugins.java.getloot.utils.Items.*;
import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class LootHelper {

    private static HashMap<Rarity, List<Material>> RARITY_LISTS = new HashMap<Rarity, List<Material>>() {{
        put(Rarity.VERY_COMMON, VERY_COMMON_ITEMS);
        put(Rarity.COMMON, COMMON_ITEMS);
        put(Rarity.RARE, RARE_ITEMS);
        put(Rarity.VERY_RARE, VERY_RARE_ITEMS);
        put(Rarity.EPIC, EPIC_ITEMS);
        put(Rarity.COMMON_CURRENCY, COMMON_CURRENCY_ITEMS);
        put(Rarity.RARE_CURRENCY, RARE_CURRENCY_ITEMS);
    }};

    public List<ItemStack> createLoot(Rarity rarity, int tries, float multiplier, List<Material> customLoot) {
        List<ItemStack> loot = createLoot(rarity, tries, multiplier);

        RARITY_LISTS.put(CUSTOM, customLoot);
        loot.addAll(buildItemStack(CUSTOM, tries, multiplier));

        return loot;
    }

    public List<ItemStack> createLoot(Rarity rarity, int tries, float multiplier) {
        List<ItemStack> loot = new ArrayList<>();

        if (rarity == Rarity.VERY_COMMON) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.COMMON) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.RARE) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.VERY_RARE) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.EPIC) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.COMMON_CURRENCY) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        if (rarity == Rarity.RARE_CURRENCY) {
            loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        return loot;
    }

    private List<ItemStack> buildItemStack(Rarity rarity, int tries, float multiplier) {
        Collections.shuffle(RARITY_LISTS.get(rarity));
        List<ItemStack> stack = new ArrayList<>();
        for (int i = 0; i <= tries; i++) {
            if (shouldAddToLoot(rarity, multiplier) && stack.size() < 1) {
                RARITY_LISTS.get(rarity).stream()
                        .findFirst()
                        .ifPresent(item -> stack.add(new ItemStack(item, 1)));
            }
        }

        return stack;
    }

    private boolean shouldAddToLoot(Rarity rarity, float multiplier) {
        float probability = (float) (random() / 2) * multiplier;
        return probability > rarity.getRarity();
    }

}

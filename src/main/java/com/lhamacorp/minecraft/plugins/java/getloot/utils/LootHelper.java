package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class LootHelper {

    private static final HashMap<Rarity, List<Material>> RARITY_LISTS = new HashMap<>() {{
        put(Rarity.VERY_COMMON, Items.VERY_COMMON_ITEMS);
        put(Rarity.COMMON, Items.COMMON_ITEMS);
        put(Rarity.RARE, Items.RARE_ITEMS);
        put(Rarity.VERY_RARE, Items.VERY_RARE_ITEMS);
        put(Rarity.EPIC, Items.EPIC_ITEMS);
        put(Rarity.COMMON_CURRENCY, Items.COMMON_CURRENCY_ITEMS);
        put(Rarity.RARE_CURRENCY, Items.RARE_CURRENCY_ITEMS);
    }};

    public List<ItemStack> createLoot(Rarity rarity, int tries, float multiplier, List<Material> customLoot) {
        List<ItemStack> loot = createLoot(rarity, tries, multiplier);

        RARITY_LISTS.put(CUSTOM, customLoot);
        loot.addAll(buildItemStack(CUSTOM, tries, multiplier));

        return loot;
    }

    public List<ItemStack> createLoot(Rarity rarity, int tries, float multiplier) {
        List<ItemStack> loot = new ArrayList<>();

        switch (rarity) {
            case VERY_COMMON, COMMON, RARE, VERY_RARE, EPIC, COMMON_CURRENCY, RARE_CURRENCY ->
                    loot.addAll(buildItemStack(rarity, tries, multiplier));
        }

        return loot;
    }

    public ItemStack createLoot(Rarity rarity) {
        List<Material> possibleItems = RARITY_LISTS.get(rarity);
        Collections.shuffle(possibleItems);

        return new ItemStack(possibleItems.get(0), 1);
    }

    private List<ItemStack> buildItemStack(Rarity rarity, int tries, float multiplier) {
        Collections.shuffle(RARITY_LISTS.get(rarity));
        List<ItemStack> stack = new ArrayList<>();
        for (int i = 0; i <= tries; i++) {
            if (shouldAddItemToLoot(rarity, multiplier) && stack.isEmpty()) {
                RARITY_LISTS.get(rarity).stream()
                        .findFirst()
                        .ifPresent(item -> stack.add(new ItemStack(item, 1)));
            }
        }

        return stack;
    }

    private boolean shouldAddItemToLoot(Rarity rarity, float multiplier) {
        float probability = (float) (random() / 2) * multiplier;
        return probability > rarity.getRarity();
    }

}

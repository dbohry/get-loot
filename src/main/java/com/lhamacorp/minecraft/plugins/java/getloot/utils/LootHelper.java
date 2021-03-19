package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class LootHelper {

    private static final List<Material> VERY_COMMON_ITEMS = Arrays.asList(
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_AXE,
            Material.WOODEN_SWORD,
            Material.PUMPKIN_SEEDS,
            Material.MELON_SEEDS,
            Material.LEATHER
    );

    private static final List<Material> COMMON_ITEMS = Arrays.asList(
            Material.STONE_SHOVEL,
            Material.STONE_PICKAXE,
            Material.STONE_AXE,
            Material.STONE_SWORD,
            Material.BEEF,
            Material.COOKED_BEEF,
            Material.BREAD
    );

    private static final List<Material> RARE_ITEMS = Arrays.asList(
            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.IRON_NUGGET,
            Material.IRON_SHOVEL,
            Material.IRON_PICKAXE,
            Material.IRON_AXE,
            Material.IRON_SWORD,
            Material.GOLD_NUGGET,
            Material.GOLDEN_SHOVEL,
            Material.IRON_INGOT
    );

    private static final List<Material> VERY_RARE_ITEMS = Arrays.asList(
            Material.EMERALD,
            Material.GOLD_INGOT,
            Material.DIAMOND,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.MAP,
            Material.COMPASS
    );

    public List<ItemStack> createLoot(Rarity rarity, int limit, float multiplier) {
        List<ItemStack> loot = new ArrayList<>();

        if (rarity == Rarity.VERY_COMMON) {
            Collections.shuffle(VERY_COMMON_ITEMS);

            List<ItemStack> veryCommonLoot = new ArrayList<>();

            for (int i = 0; i <= 5; i++) {
                if (shouldAddToLoot(rarity, multiplier) && veryCommonLoot.size() < limit) {
                    veryCommonLoot.add(new ItemStack(VERY_COMMON_ITEMS.get(i), 1));
                }
            }

            loot.addAll(veryCommonLoot);
        }

        if (rarity == Rarity.COMMON) {
            Collections.shuffle(COMMON_ITEMS);

            List<ItemStack> commonLoot = new ArrayList<>();

            for (int i = 0; i <= 4; i++) {
                if (shouldAddToLoot(rarity, multiplier) && commonLoot.size() < limit) {
                    commonLoot.add(new ItemStack(COMMON_ITEMS.get(i), 1));
                }
            }

            loot.addAll(commonLoot);
        }

        if (rarity == Rarity.RARE) {
            Collections.shuffle(RARE_ITEMS);

            List<ItemStack> rareLoot = new ArrayList<>();

            for (int i = 0; i <= 2; i++) {
                if (shouldAddToLoot(rarity, multiplier) && rareLoot.size() < limit) {
                    rareLoot.add(new ItemStack(RARE_ITEMS.get(i), 1));
                }
            }

            loot.addAll(rareLoot);
        }

        if (rarity == Rarity.VERY_RARE) {
            Collections.shuffle(VERY_RARE_ITEMS);

            List<ItemStack> veryRareLoot = new ArrayList<>();

            for (int i = 0; i <= 2; i++) {
                if (shouldAddToLoot(rarity, multiplier) && veryRareLoot.size() < limit) {
                    veryRareLoot.add(new ItemStack(VERY_RARE_ITEMS.get(i), 1));
                }
            }

            loot.addAll(veryRareLoot);
        }

        return loot;
    }

    private boolean shouldAddToLoot(Rarity rarity, float multiplier) {
        float probability = (float) (random() / 2) * multiplier;
        return probability > rarity.getRarity();
    }

}

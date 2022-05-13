package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.*;
import static com.lhamacorp.minecraft.plugins.java.getloot.utils.Items.*;
import static com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper.random;

public class LootHelper {

  private static final HashMap<Rarity, List<Material>> RARITY_LISTS = new HashMap<Rarity, List<Material>>() {{
    put(VERY_COMMON, VERY_COMMON_ITEMS);
    put(COMMON, COMMON_ITEMS);
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

    switch (rarity) {
      case VERY_COMMON:
      case COMMON:
      case RARE:
      case VERY_RARE:
      case EPIC:
      case COMMON_CURRENCY:
      case RARE_CURRENCY:
        loot.addAll(buildItemStack(rarity, tries, multiplier));
        break;
    }

    return loot;
  }

  private List<ItemStack> buildItemStack(Rarity rarity, int tries, float multiplier) {
    Collections.shuffle(RARITY_LISTS.get(rarity));
    List<ItemStack> stack = new ArrayList<>();
    for (int i = 0; i <= tries; i++) {
      if (shouldAddItemToLoot(rarity, multiplier) && stack.size() < 1) {
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

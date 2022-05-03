package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Creeper implements Mob {

  private final LootHelper helper = new LootHelper();

  public List<ItemStack> prepareLoot() {
    List<ItemStack> loot = new ArrayList<>();

    loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.030f));
    loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.020f));

    loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.30f));

    return loot;
  }

}

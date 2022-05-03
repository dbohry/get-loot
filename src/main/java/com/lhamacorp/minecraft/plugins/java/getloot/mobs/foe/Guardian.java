package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Guardian implements Mob {

  private final LootHelper helper = new LootHelper();

  public List<ItemStack> prepareLoot() {
    List<ItemStack> loot = new ArrayList<>();

    loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.05f));

    loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.045f));
    loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.030f));

    return loot;
  }

}

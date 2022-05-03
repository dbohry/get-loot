package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Model implements Mob {

  private final LootHelper helper = new LootHelper();

  public List<ItemStack> prepareLoot() {
    return null;
  }

}

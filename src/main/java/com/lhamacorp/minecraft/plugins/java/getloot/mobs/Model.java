package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Model implements Mob {

  private final LootHelper helper = new LootHelper();

  @Override
  public boolean isRightMob(EntityType type) {
    return false;
  }

  public List<ItemStack> prepareLoot() {
    return null;
  }

}

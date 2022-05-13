package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Mob {

  boolean isRightMob(EntityType type);

  List<ItemStack> prepareLoot();

}

package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface Mob {

  List<ItemStack> prepareLoot();

}

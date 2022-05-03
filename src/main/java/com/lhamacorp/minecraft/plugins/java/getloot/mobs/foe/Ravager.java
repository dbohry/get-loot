package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.*;
import static org.bukkit.entity.EntityType.RAVAGER;

public class Ravager implements Mob {

  private final LootHelper helper = new LootHelper();

  @Override
  public boolean isRightMob(EntityType type) {
    return type == RAVAGER;
  }

  public List<ItemStack> prepareLoot() {
    List<ItemStack> loot = new ArrayList<>();

    loot.addAll(helper.createLoot(EPIC, 1, 1.05f));

    loot.addAll(helper.createLoot(COMMON_CURRENCY, 1, 1.045f));
    loot.addAll(helper.createLoot(RARE_CURRENCY, 1, 1.030f));

    return loot;
  }

}

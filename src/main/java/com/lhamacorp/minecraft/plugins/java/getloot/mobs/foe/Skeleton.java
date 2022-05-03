package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static org.bukkit.Material.*;

public class Skeleton implements Mob {

  private final LootHelper helper = new LootHelper();

  public List<ItemStack> prepareLoot() {
    List<ItemStack> loot = new ArrayList<>();

    loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.025f));
    loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.015f));

    List<Material> customItems = Arrays.asList(
        ARROW,
        SPECTRAL_ARROW,
        TIPPED_ARROW,
        CROSSBOW,
        BOW
    );

    loot.addAll(helper.createLoot(CUSTOM, 1, 1, customItems));

    return loot;
  }

}

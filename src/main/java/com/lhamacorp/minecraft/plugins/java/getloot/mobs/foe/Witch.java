package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static org.bukkit.Material.*;

public class Witch implements Mob {

  private final LootHelper helper = new LootHelper();

  public List<ItemStack> prepareLoot() {
    List<ItemStack> loot = new ArrayList<>();

    loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
    loot.addAll(helper.createLoot(Rarity.RARE, 3, 1.045f));
    loot.addAll(helper.createLoot(Rarity.VERY_RARE, 3, 1.030f));
    loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.01f));

    loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.045f));
    loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.030f));

    List<Material> customItems = Arrays.asList(
        WOODEN_SWORD,
        STONE_SWORD,
        IRON_SWORD,
        DIAMOND_SWORD
    );

    Collections.shuffle(customItems);
    List<ItemStack> customLoot = helper.createLoot(CUSTOM, 1, 1, customItems);

    List<Enchantment> enchantments = Arrays.asList(
        Enchantment.FIRE_ASPECT,
        Enchantment.DAMAGE_ALL
    );

    customLoot.forEach(item -> {
      Collections.shuffle(enchantments);
      if (enchantments.stream().findFirst().isPresent()) {
        item.addEnchantment(enchantments.stream().findFirst().get(), 1);
      } else {
        System.out.println("impossible to get item");
      }
    });

    loot.addAll(customLoot);

    return loot;
  }

}

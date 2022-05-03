package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.animals.*;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.*;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GetLootFromKill implements Listener {

  private final LootHelper helper = new LootHelper();
  private final Villager villager = new Villager();
  private final Pillager pillager = new Pillager();
  private final Zoombie zoombie = new Zoombie();
  private final Skeleton skeleton = new Skeleton();
  private final Creeper creeper = new Creeper();
  private final Spider spider = new Spider();
  private final Witch witch = new Witch();
  private final Enderman enderman = new Enderman();
  private final Guardian guardian = new Guardian();
  private final ElderGuardian elderGuardian = new ElderGuardian();

  private final Chicken chicken = new Chicken();
  private final Pig pig = new Pig();
  private final Cow cow = new Cow();
  private final Sheep sheep = new Sheep();
  private final Rabbit rabbit = new Rabbit();

  @EventHandler
  public void onKill(EntityDeathEvent event) {
    LivingEntity creature = event.getEntity();

    switch (creature.getType()) {
      case VILLAGER:
      case ZOMBIE_VILLAGER:
        spawnItems(creature, villager.prepareLoot());
        break;
      case RAVAGER:
      case EVOKER:
      case ILLUSIONER:
      case VINDICATOR:
      case PILLAGER:
        spawnItems(creature, pillager.prepareLoot());
        break;
      case ZOMBIE:
      case DROWNED:
        spawnItems(creature, zoombie.prepareLoot());
        break;
      case SKELETON:
        spawnItems(creature, skeleton.prepareLoot());
        break;
      case CREEPER:
        spawnItems(creature, creeper.prepareLoot());
        break;
      case SPIDER:
        spawnItems(creature, spider.prepareLoot());
        break;
      case WITCH:
        spawnItems(creature, witch.prepareLoot());
        break;
      case ENDERMAN:
        spawnItems(creature, enderman.prepareLoot());
        break;
      case GUARDIAN:
        spawnItems(creature, guardian.prepareLoot());
        break;
      case ELDER_GUARDIAN:
        spawnItems(creature, elderGuardian.prepareLoot());
        break;
      case CHICKEN:
        spawnItems(creature, chicken.prepareLoot());
        break;
      case PIG:
        spawnItems(creature, pig.prepareLoot());
        break;
      case COW:
        spawnItems(creature, cow.prepareLoot());
        break;
      case SHEEP:
        spawnItems(creature, sheep.prepareLoot());
        break;
      case RABBIT:
        spawnItems(creature, rabbit.prepareLoot());
        break;
    }
  }

  private List<ItemStack> addCurrencyItem() {
    List<ItemStack> loot = new ArrayList<>();
    loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1));
    loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1));

    return loot;
  }

  private void spawnItems(LivingEntity entity, List<ItemStack> items) {
    if (items.isEmpty()) return;

    items.addAll(addCurrencyItem());

    for (ItemStack item : items) {
      entity
          .getWorld()
          .dropItemNaturally(entity.getLocation(), item);
    }
  }

}

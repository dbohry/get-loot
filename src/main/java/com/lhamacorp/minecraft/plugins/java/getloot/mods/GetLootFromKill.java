package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.animals.*;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.*;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetLootFromKill implements Listener {

    private final LootHelper helper = new LootHelper();
    private final List<Mob> mobs = Arrays.asList(
        new Chicken(), new Cow(), new Pig(), new Rabbit(), new Sheep(), new Bee(),
        new Creeper(),
        new ElderGuardian(),
        new Enderman(),
        new Guardian(),
        new Pillager(),
        new Skeleton(),
        new Spider(),
        new Villager(),
        new Witch(),
        new Zombie(),
        new Ravager()
    );

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity creature = event.getEntity();

        mobs.stream()
            .filter(mob -> mob.isRightMob(creature.getType()))
            .findFirst()
            .ifPresent(mob -> spawnItems(creature, mob.prepareLoot()));
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

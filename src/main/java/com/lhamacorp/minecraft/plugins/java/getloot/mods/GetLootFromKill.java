package com.lhamacorp.minecraft.plugins.java.getloot.mods;

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

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.COMMON_CURRENCY;
import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.RARE_CURRENCY;

public class GetLootFromKill implements Listener {

    private final LootHelper helper = new LootHelper();
    private final List<Mob> mobs = Arrays.asList(
            new Chicken(),
            new Cow(),
            new Pig(),
            new Rabbit(),
            new Sheep(),
            new Bee(),
            new Spider(),
            new Zombie(),
            new Slime(),
            new Skeleton(),
            new Creeper(),
            new Enderman(),
            new Witch(),
            new Pillager(),
            new Villager(),
            new Guardian(),
            new ElderGuardian(),
            new Ravager()
    );

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        LivingEntity creature = event.getEntity();

        mobs.stream()
                .filter(mob -> mob.isRightMob(creature.getType()))
                .findFirst()
                .ifPresent(mob -> spawnLoot(creature, mob.prepareLoot()));
    }

    private List<ItemStack> addCurrencyItem() {
        List<ItemStack> currency = new ArrayList<>();
        currency.addAll(helper.createLoot(COMMON_CURRENCY, 1, 1));
        currency.addAll(helper.createLoot(RARE_CURRENCY, 1, 1));

        return currency;
    }

    private void spawnLoot(LivingEntity entity, List<ItemStack> loot) {
        if (loot.isEmpty()) return;

        loot.addAll(addCurrencyItem());

        for (ItemStack item : loot) {
            entity
                    .getWorld()
                    .dropItemNaturally(entity.getLocation(), item);
        }
    }

}

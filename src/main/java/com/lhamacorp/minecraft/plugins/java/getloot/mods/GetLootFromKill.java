package com.lhamacorp.minecraft.plugins.java.getloot.mods;

import com.lhamacorp.minecraft.plugins.java.getloot.loot.DropRule;
import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootConfig;
import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootRoller;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GetLootFromKill implements Listener {

    private final LootConfig config;
    private final LootRoller roller;

    public GetLootFromKill(LootConfig config, LootRoller roller) {
        this.config = config;
        this.roller = roller;
    }

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        List<DropRule> rules = config.dropsFor(event.getEntityType());
        if (rules.isEmpty()) return;

        List<ItemStack> loot = roller.roll(rules);
        if (loot.isEmpty()) return;

        loot.addAll(roller.roll(config.bonusDrops()));

        LivingEntity entity = event.getEntity();
        loot.forEach(item -> entity.getWorld().dropItemNaturally(entity.getLocation(), item));
    }

}

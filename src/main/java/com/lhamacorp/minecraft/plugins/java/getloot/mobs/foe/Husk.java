package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static org.bukkit.Material.*;
import static org.bukkit.entity.EntityType.HUSK;

public class Husk implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == HUSK;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.015f));

        List<Material> customItems = Arrays.asList(
                HUSK_SPAWN_EGG,
                SAND,
                DEAD_BUSH,
                GOLD_NUGGET
        );

        loot.addAll(helper.createLoot(CUSTOM, 1, 1, customItems));

        return loot;
    }

}
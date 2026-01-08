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
import static org.bukkit.entity.EntityType.SHULKER;

public class Shulker implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == SHULKER;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.RARE, 4, 1.06f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1.04f));
        loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.02f));
        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.09f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.06f));

        List<Material> customItems = Arrays.asList(
                SHULKER_SHELL,
                SHULKER_BOX,
                END_STONE,
                PURPUR_BLOCK
        );

        loot.addAll(helper.createLoot(CUSTOM, 1, 1, customItems));

        return loot;
    }

}
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
import static org.bukkit.entity.EntityType.ZOMBIE;

public class Zombie implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == ZOMBIE;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 1, 1));

        List<Material> customItems = Arrays.asList(
                MELON_SEEDS,
                WHEAT_SEEDS,
                PUMPKIN_SEEDS,
                APPLE,
                POTATO,
                CARROT,
                SWEET_BERRIES,
                BEETROOT,
                BEETROOT_SEEDS
        );

        loot.addAll(helper.createLoot(CUSTOM, 1, 1, customItems));

        return loot;
    }

}

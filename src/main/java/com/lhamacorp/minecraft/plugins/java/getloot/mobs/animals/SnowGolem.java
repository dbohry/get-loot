package com.lhamacorp.minecraft.plugins.java.getloot.mobs.animals;

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
import static org.bukkit.entity.EntityType.SNOW_GOLEM;

public class SnowGolem implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == SNOW_GOLEM;
    }

    public List<ItemStack> prepareLoot() {
        List<Material> customLoot = Arrays.asList(
                SNOW_GOLEM_SPAWN_EGG,
                SNOW_BLOCK,
                CARVED_PUMPKIN,
                SNOWBALL
        );
        return new ArrayList<>(helper.createLoot(CUSTOM, 1, 1, customLoot));
    }

}
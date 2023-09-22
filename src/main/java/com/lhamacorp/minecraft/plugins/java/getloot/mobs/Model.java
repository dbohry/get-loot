package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Model implements Mob {

    @Override
    public boolean isRightMob(EntityType type) {
        return false;
    }

    public List<ItemStack> prepareLoot() {
        return null;
    }

}

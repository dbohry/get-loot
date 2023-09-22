package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.bukkit.entity.EntityType.SLIME;

public class Slime implements Mob {

    @Override
    public boolean isRightMob(EntityType type) {
        return type == SLIME;
    }

    public List<ItemStack> prepareLoot() {
        return emptyList();
    }

}

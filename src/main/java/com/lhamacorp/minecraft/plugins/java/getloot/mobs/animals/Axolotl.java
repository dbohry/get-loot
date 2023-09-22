package com.lhamacorp.minecraft.plugins.java.getloot.mobs.animals;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.CUSTOM;
import static org.bukkit.Material.AXOLOTL_SPAWN_EGG;
import static org.bukkit.entity.EntityType.AXOLOTL;

public class Axolotl implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == AXOLOTL;
    }

    public List<ItemStack> prepareLoot() {
        List<Material> customLoot = Collections.singletonList(AXOLOTL_SPAWN_EGG);
        return new ArrayList<>(helper.createLoot(CUSTOM, 1, 1, customLoot));
    }

}

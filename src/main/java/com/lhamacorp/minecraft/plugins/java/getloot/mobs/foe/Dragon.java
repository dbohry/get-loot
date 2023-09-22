package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.entity.EntityType.ENDER_DRAGON;
import static org.bukkit.entity.EntityType.WITHER;

public class Dragon implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == ENDER_DRAGON;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 3, 1.040f));
        loot.addAll(helper.createLoot(Rarity.EPIC, 2, 1.35f));
        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.050f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.035f));

        return loot;
    }

}

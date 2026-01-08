package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.entity.EntityType.VILLAGER;
import static org.bukkit.entity.EntityType.ZOMBIE_VILLAGER;

public class Villager implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == VILLAGER
                || type == ZOMBIE_VILLAGER;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.08f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 1, 1.05f));
        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.08f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.05f));

        return loot;
    }

}

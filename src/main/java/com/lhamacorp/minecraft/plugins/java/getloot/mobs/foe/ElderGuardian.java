package com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.Mob;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.entity.EntityType.ELDER_GUARDIAN;

public class ElderGuardian implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == ELDER_GUARDIAN;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.EPIC, 1, 1.10f));

        loot.addAll(helper.createLoot(Rarity.COMMON_CURRENCY, 1, 1.050f));
        loot.addAll(helper.createLoot(Rarity.RARE_CURRENCY, 1, 1.035f));

        return loot;
    }

}

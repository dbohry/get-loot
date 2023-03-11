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
import static org.bukkit.Material.COBWEB;
import static org.bukkit.Material.SPIDER_SPAWN_EGG;
import static org.bukkit.entity.EntityType.SPIDER;

public class Spider implements Mob {

    private final LootHelper helper = new LootHelper();

    @Override
    public boolean isRightMob(EntityType type) {
        return type == SPIDER;
    }

    public List<ItemStack> prepareLoot() {
        List<ItemStack> loot = new ArrayList<>();

        loot.addAll(helper.createLoot(Rarity.VERY_COMMON, 5, 1));
        loot.addAll(helper.createLoot(Rarity.COMMON, 4, 1));
        loot.addAll(helper.createLoot(Rarity.RARE, 2, 1.010f));
        loot.addAll(helper.createLoot(Rarity.VERY_RARE, 2, 1));

        List<Material> customItems = Arrays.asList(
                SPIDER_SPAWN_EGG,
                COBWEB
        );

        loot.addAll(helper.createLoot(CUSTOM, 1, 1, customItems));

        return loot;
    }

}

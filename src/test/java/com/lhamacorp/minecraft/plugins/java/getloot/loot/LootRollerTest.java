package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.bukkit.Material.DIAMOND;
import static org.bukkit.Material.STONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LootRollerTest {

    private static final List<DropRule> TEN_PERCENT =
            List.of(new DropRule(List.of(STONE), 900, 1, 1, List.of()));

    private static Random fixed(double value) {
        return new Random() {
            @Override
            public double nextDouble() {
                return value;
            }

            @Override
            public int nextInt(int bound) {
                return 0;
            }
        };
    }

    @Test
    public void dropsWhenRollIsBelowChance() {
        List<ItemStack> loot = new LootRoller(fixed(0.05)).roll(TEN_PERCENT);

        assertEquals(1, loot.size());
        assertEquals(STONE, loot.get(0).getType());
    }

    @Test
    public void dropsNothingWhenRollIsAboveChance() {
        assertTrue(new LootRoller(fixed(0.5)).roll(TEN_PERCENT).isEmpty());
    }

    @Test
    public void pickReturnsAnItemOfTheTier() {
        Tier tier = new Tier("RARE", 990, List.of(DIAMOND));

        assertEquals(DIAMOND, new LootRoller(new Random()).pick(tier).getType());
    }
}

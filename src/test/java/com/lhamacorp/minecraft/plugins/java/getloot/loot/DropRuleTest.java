package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.junit.Test;

import java.util.List;

import static org.bukkit.Material.STONE;
import static org.junit.Assert.assertEquals;

public class DropRuleTest {

    private static DropRule rule(int threshold, int tries, double multiplier) {
        return new DropRule(List.of(STONE), threshold, tries, multiplier, List.of());
    }

    @Test
    public void thresholdMapsToChancePerTry() {
        assertEquals(0.10, rule(900, 1, 1).chancePerTry(), 1e-9);
        assertEquals(0.01, rule(990, 1, 1).chancePerTry(), 1e-9);
        assertEquals(0.50, rule(500, 1, 1).chancePerTry(), 1e-9);
    }

    @Test
    public void thresholdOfOneThousandNeedsAMultiplier() {
        assertEquals(0, rule(1000, 3, 1).chance(), 0);
        assertEquals(1 - 1 / 1.35, rule(1000, 1, 1.35).chancePerTry(), 1e-9);
    }

    @Test
    public void chanceIsClamped() {
        assertEquals(1, rule(-500, 1, 1).chancePerTry(), 0);
        assertEquals(0, rule(2000, 1, 1).chancePerTry(), 0);
    }

    @Test
    public void triesCompoundTheChance() {
        assertEquals(1 - 0.9 * 0.9 * 0.9, rule(900, 3, 1).chance(), 1e-9);
    }
}

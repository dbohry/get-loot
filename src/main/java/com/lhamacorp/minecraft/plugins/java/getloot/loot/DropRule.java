package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

/**
 * One configured drop: rolled {@code tries} times, producing at most one of {@code items}.
 */
public record DropRule(List<Material> items,
                       int threshold,
                       int tries,
                       double multiplier,
                       List<Enchantment> enchantments) {

    static final int SCALE = 1000;

    /** Chance that a single roll succeeds. */
    public double chancePerTry() {
        double chance = 1 - threshold / (SCALE * multiplier);
        return Math.max(0, Math.min(1, chance));
    }

    /** Chance that at least one of the rolls succeeds. */
    public double chance() {
        return 1 - Math.pow(1 - chancePerTry(), tries);
    }
}

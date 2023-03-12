package com.lhamacorp.minecraft.plugins.java.getloot.enums;

import java.util.Arrays;
import java.util.List;

public enum Rarity {

    VERY_COMMON(900),
    COMMON(950),
    RARE(990),
    VERY_RARE(999),
    EPIC(1000),
    CUSTOM(900),
    COMMON_CURRENCY(500),
    RARE_CURRENCY(990);

    private final int rarity;

    Rarity(int rarity) {
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }

    public static Rarity rarityFromScore(int score) {
        List<Rarity> rarities = Arrays.stream(Rarity.values()).toList();

        int distance = Math.abs(rarities.get(0).getRarity() - score);
        int i = 0;

        for (int c = 1; c < rarities.size(); c++) {
            int cdistance = Math.abs(rarities.get(c).getRarity() - score);
            if (cdistance < distance) {
                i = c;
                distance = cdistance;
            }
        }

        return rarities.get(i);
    }
}

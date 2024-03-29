package com.lhamacorp.minecraft.plugins.java.getloot.enums;

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
        Rarity closestRarity = null;
        int minDistance = Integer.MAX_VALUE;

        for (Rarity rarity : Rarity.values()) {
            int distance = Math.abs(rarity.getRarity() - score);
            if (distance < minDistance) {
                minDistance = distance;
                closestRarity = rarity;
            }
        }

        return closestRarity;
    }
}

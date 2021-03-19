package com.lhamacorp.minecraft.plugins.java.getloot.enums;

public enum Rarity {

    VERY_COMMON(30),
    COMMON(50),
    RARE(80),
    RAREST(95);

    private final int rarity;

    Rarity(int rarity) {
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }
}

package com.lhamacorp.minecraft.plugins.java.getloot.enums;

public enum Rarity {

    VERY_COMMON(900),
    COMMON(950),
    RARE(990),
    VERY_RARE(999),
    EPIC(1000);


    private final int rarity;

    Rarity(int rarity) {
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }
}

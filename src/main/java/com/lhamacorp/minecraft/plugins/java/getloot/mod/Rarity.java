package com.lhamacorp.minecraft.plugins.java.getloot.mod;

public enum Rarity {

    COMMON(3),
    RARE(7),
    RAREST(9);

    private final int rarity;

    Rarity(int rarity) {
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }
}

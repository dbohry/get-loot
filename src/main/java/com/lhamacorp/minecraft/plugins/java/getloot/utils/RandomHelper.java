package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import java.util.Random;

public class RandomHelper {

    private static final Random RANDOM = new Random();
    private static final int MIN = 1;
    private static final int MAX = 2000;

    public static Integer random(int max, int min) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public static Integer random() {
        return random(MAX, MIN);
    }

}

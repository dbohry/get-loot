package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.Material;

import java.util.Set;

public record ExchangeRecipe(Set<Material> inputs, Tier reward) {
}

package com.lhamacorp.minecraft.plugins.java.getloot.loot;

import org.bukkit.Material;

import java.util.List;

public record Tier(String name, int threshold, List<Material> items) {
}

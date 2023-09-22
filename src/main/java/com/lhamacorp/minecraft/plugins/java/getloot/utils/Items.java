package com.lhamacorp.minecraft.plugins.java.getloot.utils;

import com.lhamacorp.minecraft.plugins.java.getloot.config.PropertiesConfiguration;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.bukkit.Material.*;

class Items {
    static final List<Material> COMMON_CURRENCY_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.common-currency")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> RARE_CURRENCY_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.rare-currency")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> VERY_COMMON_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.very-common")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> COMMON_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.common")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> RARE_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.rare")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> VERY_RARE_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.very-rare")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

    static final List<Material> EPIC_ITEMS = Arrays.stream(PropertiesConfiguration.PROPERTIES
                    .get("items.epic")
                    .toString()
                    .split(","))
            .map(Material::valueOf).collect(toList());

}

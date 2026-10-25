package com.lhamacorp.minecraft.plugins.java.getloot;

import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootConfig;
import com.lhamacorp.minecraft.plugins.java.getloot.loot.LootRoller;
import com.lhamacorp.minecraft.plugins.java.getloot.mods.ExchangeCurrency;
import com.lhamacorp.minecraft.plugins.java.getloot.mods.GetLootFromKill;
import org.bukkit.Registry;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        LootConfig config = LootConfig.load(getConfig(), getLogger(), Registry.ENCHANTMENT::get);
        LootRoller roller = new LootRoller(new Random());

        getServer().getPluginManager().registerEvents(new GetLootFromKill(config, roller), this);
        getServer().getPluginManager().registerEvents(new ExchangeCurrency(config, roller), this);
    }

}

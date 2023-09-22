package com.lhamacorp.minecraft.plugins.java.getloot;

import com.lhamacorp.minecraft.plugins.java.getloot.mods.ExchangeCurrency;
import com.lhamacorp.minecraft.plugins.java.getloot.mods.GetLootFromKill;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("GetLook is enabled!");
        getServer().getPluginManager().registerEvents(new GetLootFromKill(), this);

        System.out.println("Exchange currencies is enabled!");
        getServer().getPluginManager().registerEvents(new ExchangeCurrency(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("GetLook is disabled!");
    }
}

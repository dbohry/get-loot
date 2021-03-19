package com.lhamacorp.minecraft.plugins.java.getloot;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("GetLook is enabled!");
        getServer().getPluginManager().registerEvents(new GetLoot(), this);
        getServer().getPluginManager().registerEvents(new BreakIntoSmallPart(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("GetLook is disabled!");
    }
}

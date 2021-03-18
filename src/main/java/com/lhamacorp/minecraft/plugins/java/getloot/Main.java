package com.lhamacorp.minecraft.plugins.java.getloot;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("GetLook is enabled!");
        getServer().getPluginManager().registerEvents(new GetLoot(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

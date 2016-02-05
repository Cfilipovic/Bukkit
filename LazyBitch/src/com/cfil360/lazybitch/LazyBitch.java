package com.cfil360.lazybitch;

import com.cfil360.lazybitch.listeners.CreeperListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 8/2/2015.
 */
public class LazyBitch extends JavaPlugin {

    public void onEnable() {
        this.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new CreeperListener(), this);
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("LazyBitch");
    }
}

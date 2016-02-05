package com.cfil360.kitpvp;

import com.cfil360.kitpvp.Listeners.JoinListener;
import com.cfil360.kitpvp.Listeners.KillListener;
import com.cfil360.kitpvp.Listeners.KitSelection;
import com.cfil360.kitpvp.Listeners.MatchSelection;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 3/26/2015.
 */
public class KitPvP extends JavaPlugin implements Listener{

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new KitSelection(), this);
        Bukkit.getPluginManager().registerEvents(new KillListener(), this);
        Bukkit.getPluginManager().registerEvents(new MatchSelection(), this);

        saveDefaultConfig();
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("KitPvP");
    }
}

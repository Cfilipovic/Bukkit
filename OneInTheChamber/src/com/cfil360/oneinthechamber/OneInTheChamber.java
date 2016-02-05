package com.cfil360.oneinthechamber;

import com.cfil360.oneinthechamber.Listeners.DeathListener;
import com.cfil360.oneinthechamber.Listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 5/25/2015.
 */
public class OneInTheChamber extends JavaPlugin {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }
}

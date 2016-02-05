package com.cfil360.newser;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 5/19/2015.
 */
public class Newser extends JavaPlugin {
    static FileConfiguration config;

    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("news")) {
            sender.sendMessage(getNews());
            return true;
        }
        return false;
    }

    public static String getNews() {
        String news;
        news = ChatColor.AQUA + "---------------News and Events---------------";
        for(String key : config.getKeys(false)){
            news = news + "\n" + ChatColor.YELLOW + "-- " + config.getString(key);
        }
        news = news + "\n" + ChatColor.AQUA + "--------------------------------------------";

        return news;
    }
}

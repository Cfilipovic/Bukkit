package com.cfil360.AdvancedBan;

import com.cfil360.AdvancedBan.Listeners.ChatListener;
import com.cfil360.AdvancedBan.Listeners.JoinListener;
import com.cfil360.AdvancedBan.Util.CommandManager;
import com.cfil360.AdvancedBan.Util.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by connor on 6/5/2014.
 */
public class AdvancedBan extends JavaPlugin {
    public static MySQL mySQL;
    String prefix = "§8[§2AdvancedBan§8]";

    public void onEnable() {
        mySQL = new MySQL("localhost", "root", "connor1", "minecraft");
        getCommand("admin").setExecutor(new CommandManager());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}

package com.cfil360.chatlogger;


import com.cfil360.chatlogger.Listeners.ChatListener;
import com.cfil360.chatlogger.Util.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by connor on 7/11/2014.
 */
public class ChatLogger extends JavaPlugin {

    static MySQL mySQL;

    public void onEnable() {
        mySQL = new MySQL("localhost", "root", "connor1", "Minecraft");
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}

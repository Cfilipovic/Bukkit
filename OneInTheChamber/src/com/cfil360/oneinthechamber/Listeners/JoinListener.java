package com.cfil360.oneinthechamber.Listeners;

import com.cfil360.oneinthechamber.Arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Connor on 5/25/2015.
 */
public class JoinListener implements Listener {

    public void onJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("yay");
        Arena.getArena().addPlayer(event.getPlayer());

        event.getPlayer().sendMessage("yay");
    }
}

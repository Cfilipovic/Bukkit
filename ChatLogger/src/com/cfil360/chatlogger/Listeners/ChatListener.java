package com.cfil360.chatlogger.Listeners;

import com.cfil360.chatlogger.ChatLogger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by connor on 7/11/2014.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        ChatLogger.getMySQL().insertChat(p, message);
    }
}

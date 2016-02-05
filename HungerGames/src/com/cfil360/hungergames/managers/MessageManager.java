package com.cfil360.hungergames.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Connor on 10/4/2015.
 */
public class MessageManager {
    private static MessageManager instance = new MessageManager();
    private String PREFIX = "§b[§aHungerGames§b] §f";

    public static MessageManager getInstance() {
        return instance;
    }

    public void message(Player player, String message) {
        message.replaceAll("&", "§");
        player.sendMessage(PREFIX + player);
    }

    public void broadcast(ArrayList<Player> players, String message) {
        for(Player player : players) {
            message(player, message);
        }
    }
}

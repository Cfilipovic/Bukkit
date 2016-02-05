package com.cfil360.kitpvp.Util;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Connor on 4/20/2015.
 */
public class Freezer {
    ArrayList<Player> frozen = new ArrayList<Player>();

    private static Freezer instance = new Freezer();

    public static Freezer getInstance() {
        return instance;
    }

    public void freeze(Player player) {
        frozen.add(player);
    }

    public void unfreeze(Player player) {
        frozen.remove(player);
    }

    public ArrayList<Player> getFrozenPlayers() {
        return frozen;
    }
}

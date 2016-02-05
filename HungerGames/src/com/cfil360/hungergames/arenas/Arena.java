package com.cfil360.hungergames.arenas;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 10/4/2015.
 */
public class Arena {

    //name
    //spawn locations
    //players

    private String name;
    private List<Location> locations = new ArrayList<Location>();
    private List<Player> players = new ArrayList<Player>();

    public Arena(String name, List<Location> locations) {
        this.name = name;
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}

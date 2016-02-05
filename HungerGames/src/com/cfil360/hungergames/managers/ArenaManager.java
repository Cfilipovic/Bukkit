package com.cfil360.hungergames.managers;

import com.cfil360.hungergames.arenas.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 10/4/2015.
 */
public class ArenaManager {
    private static ArenaManager instance = new ArenaManager();

    public static ArenaManager getInstance() {
        return instance;
    }

    List<Arena> arenas = new ArrayList<Arena>();

    public boolean isArena(String name) {
        for(Arena arena : arenas) {
            if(arena.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Arena getArena(String name) {
        for(Arena arena : arenas) {
            if(arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }
        return null;
    }

    public void addPlayer(Player player, String name) {
        Arena arena = this.getArena(name);

        //Make sure the arena is legit
        if(arena == null) {
            MessageManager.getInstance().message(player, "&cInvalid arena!");
            return;
        }

        //Make sure the player doesn't join 2 games
        if(isInGame(player)) {
            MessageManager.getInstance().message(player, "&cYou are already in a game!");
            return;
        }

        // Adds the player to the arena
        arena.addPlayer(player);
    }

    public void createArena(String name, List<Location> spawnLocations) {
        Arena arena = new Arena(name, spawnLocations);
        return;
    }

    public boolean isInGame(Player player) {
        for(Arena arena : arenas) {
            if(arena.getPlayers().contains(player)) {
                return true;
            }
        }

        return false;
    }

    public String serializeLoc(Location l){
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }
}

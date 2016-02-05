package com.cfil360.kitpvp.Matches;

import java.util.ArrayList;

/**
 * Created by Connor on 4/20/2015.
 */
public class ArenaManager {
    ArrayList<Arena> arenas = new ArrayList<Arena>();

    private static ArenaManager instance = new ArenaManager();

    public static ArenaManager getInstance() {
        return instance;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public Arena getOpenArena() {
        if(arenas.size() > 0) return arenas.get(0);
        return null;
    }
}

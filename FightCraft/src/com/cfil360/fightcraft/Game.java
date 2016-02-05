package com.cfil360.fightcraft;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Connor on 10/2/2015.
 */
public class Game {

    public enum GameState {
        COUNTDOWN, ACTIVE
    }

    private Map map;
    private GameState state;
    private ArrayList<Player> players;

    public Game(Map map, ArrayList<Player> players) {
        this.map = map;
        this.state = GameState.COUNTDOWN;
        this.players = players;
    }

    public GameState getState() {
        return state;
    }

    public boolean containsPlayer(Player p) {
        return players.contains(p);
    }

    public void broadcast(String message) {
        for(Player p : players) {
            p.sendMessage(message);  /// You could add a prefix
        }
    }
}

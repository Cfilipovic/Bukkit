package com.cfil360.fightcraft;

import org.bukkit.entity.Player;

/**
 * Created by Connor on 9/6/2015.
 */
public class GamePlayer {

    private Player player;

    private int points;
    private int kills;
    private int deaths;

    public GamePlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

    public void modifyPoints(int mod) {
        this.points += mod;
        //update database
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        this.kills += 1;
        //update database
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeath() {
        this.deaths += 1;
        //update database
    }

    public double getKDR() {
        if(deaths == 0) {
            return kills;
        }

        return kills / deaths;
    }
}

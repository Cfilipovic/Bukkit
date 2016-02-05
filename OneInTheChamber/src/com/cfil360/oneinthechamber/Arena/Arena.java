package com.cfil360.oneinthechamber.Arena;

import com.cfil360.oneinthechamber.Util.Countdown;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Connor on 5/25/2015.
 */
public class Arena {

    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Player> dead = new ArrayList<Player>();
    private static Location lobby = new Location(Bukkit.getWorld("world"), 0, 0, 0);
    private static ArrayList<Location> spawns = new ArrayList<Location>();
    private static Player winner;

    public Arena() {}

    public Arena(ArrayList<Player> dead, Location lobby, ArrayList<Location> spawns, Player winner) {
        this.dead = dead;
        this.lobby = lobby;
        this.spawns = spawns;
        this.winner = winner;
    }

    public static boolean isPlayer(Player player) {
        if(players.contains(player)) return true;
        return false;
    }

    public static Arena getArena() {
        return new Arena(dead, lobby, spawns, winner);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getDead() {
        return dead;
    }

    public void setDead(ArrayList<Player> dead) {
        this.dead = dead;
    }

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public ArrayList<Location> getSpawns() {
        return spawns;
    }

    public void setSpawns(ArrayList<Location> spawns) {
        this.spawns = spawns;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void addPlayer(Player player) {
        if(players.contains(player)) {
            player.sendMessage("§4§l>> You are already in a game!");
            return;
        }

        players.add(player);
        message("§6§l>>§9" + player.getName() + " §e has joined the game!");

        if(players.size() > 2) {
            //start the game
            start();
        }
    }

    public void removePlayer(Player player) {
        if(!players.contains(player)) return;

        players.remove(player);
        message("§6§l>>§9" + player.getName() + " §7 has left the game!");

        if(players.size() < 2) {
            for(Player p : players) {
                dead.add(p);
            }
            //end the game
            end();
        }
    }

    public void message(String message) {
        for(Player player : players) {
            player.sendMessage(message);
        }
    }

    public void sound(Sound sound) {
        for(Player player : players) {
            player.getLocation().getWorld().playSound(player.getLocation(), sound, 1, 1);
        }
    }

    public void start() {
        for(Player player : players) {
            //teleport the players
            player.teleport(spawns.get(players.indexOf(player)));

            //heal the players
            player.setHealth(20);

            //feed the players
            player.setFoodLevel(20);

            //give the players items
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.getInventory().addItem(new ItemStack(Material.ARROW));

            //start a countdown
            new Countdown(this, 30, 30, 20, 10, 5, 4, 3, 2, 1).runTaskTimerAsynchronously(Bukkit.getPluginManager().getPlugin("OneInTheChamber"), 0, 20);

        }
    }

    public void end() {
        for(Player player : dead) {
            player.sendMessage("§6§l>>§9 " + players.get(0).getName() + " §7has won the game");
            player.teleport(lobby);
        }
    }
}

package com.cfil360.pointsapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by connor on 6/5/2014.
 */
public class PointsAPI extends JavaPlugin implements Listener{
    static MySQL mySQL;
    String prefix = "§8[§2PointsAPI§8]";

    public void onEnable() {
        mySQL = new MySQL("localhost", "root", "connor1", "minecraft");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(e.getPlayer().hasPlayedBefore()) return;

        insertPoints(e.getPlayer(), 0);
    }

    public static void insertPoints(Player player, int points) {
        mySQL.insertPoints(player.getUniqueId(), points);
        //inserts points into the player under the player's UUID
    }

    public static void addPoints(Player player, int points) {
        if(getPoints(player) == 0) {
            mySQL.insertPoints(player.getUniqueId(), points);
        }
        else mySQL.updatePoints(player, getPoints(player) + points);
        //add points to the player
    }

    public static void removePoints(Player player, int points) {
        mySQL.updatePoints(player, getPoints(player) - points);
        //remove points from the player
    }

    public static int getPoints(Player player) {
        return mySQL.getPoints(player);
        //returns the points of the player passed
    }

    public static void insertPoints(OfflinePlayer player, int points) {
        mySQL.insertPoints(player.getUniqueId(), points);
        //inserts points into the player under the player's UUID
    }

    public static void addPoints(OfflinePlayer player, int points) {
        if(getPoints(player) == 0) {
            mySQL.insertPoints(player.getUniqueId(), points);
        }
        else mySQL.updatePoints(player, getPoints(player) + points);
        //add points to the player
    }

    public static void removePoints(OfflinePlayer player, int points) {
        mySQL.updatePoints(player, getPoints(player) - points);
        //remove points from the player
    }

    public static int getPoints(OfflinePlayer player) {
        return mySQL.getPoints(player);
        //returns the points of the player passed
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("points")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                return true;
            }

            Player player = (Player) sender;

            if (args.length == 0) {
                sendMessage(player, "§bYou have §e" + PointsAPI.getPoints(player) + " §bpoints.");
                return true;
            } else if(args[0].equalsIgnoreCase("add")) {
                if(args.length != 3) {
                    sendMessage(player, ChatColor.RED + "Usage: /points add [player] [points]");
                    return true;
                }
                else {
                    try {
                        addPoints(Bukkit.getOfflinePlayer(args[1]), Integer.parseInt(args[2]));
                        sendMessage(player, ChatColor.YELLOW + args[2] + " §bpoints have been added to §e" + args[1] + ".");
                    } catch(Exception ex) { sendMessage(player, ChatColor.RED + "An error occured while executing.  Double check command syntax."); }
                }
                return true;
            } else if(args[0].equalsIgnoreCase("subtract")) {
                if(args.length != 3) {
                    sendMessage(player, ChatColor.RED + "Usage: /points subtract [player] [points]");
                    return true;
                }
                else {
                    try {
                        removePoints(Bukkit.getOfflinePlayer(args[1]), Integer.parseInt(args[2]));
                        sendMessage(player, ChatColor.YELLOW + args[2] + " §bpoints have been subtracted from §e" + args[1]);
                    } catch(Exception ex) { sendMessage(player, ChatColor.RED + "An error occured while executing.  Double check command syntax."); }
                }
                return true;
            } else if(args[0].equalsIgnoreCase("help")) {
                sendMessage(player, ChatColor.YELLOW + "Usage: /points [player]");
                sendMessage(player, ChatColor.YELLOW + "Usage: /points add [player] [points]");
                sendMessage(player, ChatColor.YELLOW + "Usage: /points subtract [player] [points]");

                return true;
            } else if (args.length == 1) {
                sendMessage(player, ChatColor.YELLOW + args[0] + " §bhas §e" + PointsAPI.getPoints(Bukkit.getOfflinePlayer(args[0])) + " §bpoints.");
                return true;
            } else {
                sendMessage(player, "§cCorrect usage: /points [player]");
                return true;
            }
        }
        return false;
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(prefix + " " + message);
    }
}

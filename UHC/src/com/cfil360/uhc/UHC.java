package com.cfil360.uhc;

import com.cfil360.gamecore.GameManager.*;
import com.cfil360.gamecore.MySQLManager.MySQL;
import com.cfil360.uhc.Commands.CommandManager;
import com.cfil360.uhc.Executors.EndGameExecutor;
import com.cfil360.uhc.Executors.StartGameExecutor;
import com.cfil360.uhc.Maps.asfdasf;
import com.cfil360.uhc.Util.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 4/26/2015.
 */
public class UHC extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("UHC").setExecutor(new CommandManager());

        //add the game to the list of games
        GameManager.getInstance().addGame(setupGame());
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + getGame().getName() + " successfully loaded");

        //load the api
        API.setup(getGame().getMySQL());
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MySQL successfully loaded");

        //load the maps for the game
        com.cfil360.gamecore.MapManager.MapManager.getInstance().setup(getGame());
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Maps successfully loaded");

        //load the arenas for the game
        asfdasf.getInstance().setup();
    }

    public static Game getGame() {
        return GameManager.getInstance().getGame("UHC");
    }

    public Game setupGame() {
        //set basic game info
        String name = "UHC";
        ChatColor chat = ChatColor.AQUA;

        //set the game rules
        GameRules rules = new GameRules();
        rules.setBuild(false);
        rules.setPvp(true);
        rules.setPve(true);
        rules.setDrop(true);
        rules.setBuckets(true);
        rules.setDestroy(false);
        rules.setPickup(true);

        //set the game executors
        GameExecutors executors = new GameExecutors();
        executors.setStartGameExecutor(new StartGameExecutor());
        executors.setEndGameExecutor(new EndGameExecutor());

        //set the mysql
        MySQL mySQL = new MySQL(name.replaceAll(" ",""));

        //set the stat handler
        GameStats gameStats = new GameStats();
        gameStats.setKills(true);
        gameStats.setDeaths(true);
        gameStats.setWins(true);
        gameStats.setLosses(true);

        //set the selection item
        ItemStack selection = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = selection.getItemMeta();
        meta.setDisplayName("§e§l" + name);
        List<String> lore = new ArrayList<String>();
        lore.add("§724 players battle to the death.");
        lore.add("§7Last player standing wins.");
        lore.add("§7May the odds be forever in your favor");
        lore.add("");
        lore.add("§2§l> §r§aCLICK to view servers! §2§l<");
        meta.setLore(lore);
        selection.setItemMeta(meta);

        Game game = new Game(name, chat, rules, executors, gameStats, mySQL, selection);

        return game;
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("UHC");
    }
}

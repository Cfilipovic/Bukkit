package com.cfil360.kitpvp.Matches;

import com.cfil360.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Connor on 4/20/2015.
 */
public class MatchManager {
    ArrayList<Match> matches = new ArrayList<Match>();
    private Inventory gui;

    private static MatchManager instance = new MatchManager();

    public static MatchManager getInstance() {
        return instance;
    }

    private MatchManager() {
        gui = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + "Match Selector");

        matches.add(new Match(Bukkit.getPlayer("Cfil360"),"Diamond", ArenaManager.getInstance().getOpenArena()));

        for (Match match : matches) {
            gui.addItem(match.getSelectionItem());
        }

        //add the create match item
        gui.setItem(26, getNewMatchItem());
    }

    public void loadArenas() {
        FileConfiguration config = KitPvP.getPlugin().getConfig();
        for(String id : config.getConfigurationSection("Arenas").getKeys(false)) {
            Location location1 = new Location(Bukkit.getWorld(config.getString(id + ".World")), config.getInt(id + ".X"), config.getInt(id + ".Y"), config.getInt(id + ".Z"));
            Location location2 = new Location(Bukkit.getWorld(config.getString(id + ".World")), config.getInt(id + ".X"), config.getInt(id + ".Y"), config.getInt(id + ".Z"));

            Arena arena = new Arena(location1, location2);

            //add to the list of open arenas
            ArenaManager.getInstance().addArena(arena);
        }
    }

    public Inventory getGUI() {
        return gui;
    }

    public ItemStack getMatchSelector() {
        ItemStack kitSelector = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = kitSelector.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Match Selector");
        meta.setLore(Arrays.asList("Right click this", "to choose", "your match."));
        kitSelector.setItemMeta(meta);

        return kitSelector;
    }


    public ItemStack getNewMatchItem() {
        ItemStack item = new ItemStack(Material.WOOL);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Create Match");

        item.setItemMeta(meta);

        return item;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
    }

    public ArrayList<Match> getPlayers() {
        return matches;
    }

    public Match getPlayerMatch(Player player) {
        for(Match match : matches) {
            if(match.getPlayers().contains(player)) {
                return match;
            }
        }
        return null;
    }
}

package com.cfil360.uhc.Maps;

import com.cfil360.gamecore.ArenaManager.Arena;
import com.cfil360.gamecore.ArenaManager.ArenaManager;
import com.cfil360.gamecore.GameManager.Game;
import com.cfil360.gamecore.GameManager.GameManager;
import com.cfil360.gamecore.MapManager.Map;
import com.cfil360.gamecore.MapManager.MapManager;
import com.cfil360.uhc.Util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Connor on 4/26/2015.
 */
public class asfdasf {

    private static asfdasf instance = new asfdasf();

    public static asfdasf getInstance() {
        return instance;
    }

    public void setup() {
        {
            try {
                int id = 1;
                ArenaManager.getInstance().addArena(getArena(id));
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Arena is incorrectly setup!");
                e.printStackTrace();
            }
        }
        {
            int id = 2;
            //arenas.add(getArena(id));
        }
        {
            int id = 3;
            //arenas.add(getArena(id));
        }
        {
            int id = 4;
            //arenas.add(getArena(id));
        }
    }

    public Arena getArena(int id) {
        Game game = getGame(id);
        String name = getName(id);
        int maxPlayers = getMaxPlayers(id);
        Location lobby = new Location(getLobby(id).getWorld(), getLobby(id).getBlockX(), getLobby(id).getWorld().getHighestBlockYAt(getLobby(id)), getLobby(id).getBlockZ());
        Map map = MapManager.getInstance().getAllMaps().get(0);
        ItemStack selection = getSelection();
        ArrayList<Location> spawns = loadSpawns(id);
        return new Arena(game, id, name,  lobby, map, selection);
    }

    public Game getGame(int id) {
        return GameManager.getInstance().getGame(SettingsManager.getArenas().getConfigFile().getString(id + ".Game"));
    }

    public String getName(int id) {
        return SettingsManager.getArenas().get(id + ".Name");
    }

    public int getMaxPlayers(int id) {
        return SettingsManager.getArenas().get(id + ".MaxPlayers");
    }

    public Location getLobby(int id) {
        return Arena.deserializeLoc(SettingsManager.getArenas().getConfigFile().getString(id + ".Lobby"));
    }

    public ItemStack getSelection() {
        return new ItemStack(Material.MAP);
    }

    public ArrayList<Location> loadSpawns(int id) {
        ArrayList<Location> spawns = new ArrayList<Location>();

        for(String s : SettingsManager.getArenas().getConfigFile().getStringList("Arenas." + id + ".Spawns")) {
            spawns.add(Arena.deserializeLoc(s));
        }

        return spawns;
    }
}

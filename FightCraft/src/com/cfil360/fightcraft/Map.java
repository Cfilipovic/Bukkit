package com.cfil360.fightcraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;

/**
 * Created by Connor on 9/17/2015.
 */
public class Map {
    private World world;
    private ArrayList<Location> spawnLocations;
    private ArrayList<Chest> chests;

    private boolean inUse;
    private int spawnIndex;

    public Map(ConfigurationSection section) {
        this.spawnLocations = new ArrayList<Location>();
        this.chests = new ArrayList<Chest>();

        //world
        this.world = Bukkit.getServer().getWorld(section.getString("world"));

        //spawnLocations
        for(String key : section.getConfigurationSection("spawnLocations").getKeys(false)) {
            // 1, 2, 3
            ConfigurationSection location = section.getConfigurationSection(key);
            spawnLocations.add(new Location(world, location.getDouble("x"), location.getDouble("y"), location.getDouble("z"), (float) location.getDouble("pitch"), (float) location.getDouble("yaw")));

        }

        //chests
        for(String key : section.getConfigurationSection("chests").getKeys(false)) {
            // 1, 2, 3
            ConfigurationSection location = section.getConfigurationSection(key);
            Location chestLocation = new Location(world, location.getDouble("x"), location.getDouble("y"), location.getDouble("z"));

            if(chestLocation.getBlock().getType() == Material.CHEST || chestLocation.getBlock().getType() == Material.TRAPPED_CHEST) {
                chests.add((Chest) chestLocation.getBlock().getState());
            }
            else {
                section.getConfigurationSection("chests").set(key, null);
                System.err.println("Removed saved chest at " + chestLocation + " because it doesn't exist.");
            }
        }
    }

    public Location getNextSpawnLocation() {
        if(spawnIndex >= spawnLocations.size()) {
            return null;
        }

        return spawnLocations.get(spawnIndex++);
    }

    public boolean inUse() {
        return inUse;
    }

    public void setInUse() {
        this.inUse = true;
    }

    public void reset() {
        inUse = false;
        spawnIndex = 0;
    }
}

    /*
    maps:
      0:
        world: Map1
        spawnLocations:
          0:
            x: 123
            y: 67
            z: 105
            pitch: 145
            yaw 238
        chests:
          0:
            x: 4
            y: 10
            z: 203
     */

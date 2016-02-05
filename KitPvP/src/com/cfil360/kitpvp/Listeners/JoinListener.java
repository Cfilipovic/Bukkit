package com.cfil360.kitpvp.Listeners;

import com.cfil360.kitpvp.KitPvP;
import com.cfil360.kitpvp.Kits.KitManager;
import com.cfil360.kitpvp.Matches.MatchManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Connor on 3/27/2015.
 */
public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        //give the player the kit selection item
        item(player);

        //teleport player to spawn
        teleport(player);
    }

    private void item(Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        p.getInventory().addItem(KitManager.getInstance().getKitSelector());
        p.getInventory().addItem(MatchManager.getInstance().getMatchSelector());
        p.updateInventory();
    }

    private void teleport(Player p) {
        FileConfiguration config = KitPvP.getPlugin().getConfig();

        World world = Bukkit.getWorld(config.getString("Spawn.World"));
        int x = config.getInt("Spawn.X");
        int y = config.getInt("Spawn.Y");
        int z = config.getInt("Spawn.Z");

        //define a location
        Location location = new Location(world, x, y, z);
        location = new Location(location.getWorld(), location.getBlockX(), location.getWorld().getHighestBlockYAt(location), location.getBlockZ());

        //tp the player
        p.teleport(location);
    }
}

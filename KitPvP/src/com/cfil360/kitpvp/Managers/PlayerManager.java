package com.cfil360.kitpvp.Managers;

import com.cfil360.kitpvp.Kits.Global.KitPvPPlayer;
import com.cfil360.kitpvp.Kits.Kit;
import com.cfil360.kitpvp.Kits.KitManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Connor on 4/19/2015.
 */
public class PlayerManager {
    ArrayList<KitPvPPlayer> players = new ArrayList<KitPvPPlayer>();

    private static PlayerManager instance = new PlayerManager();

    public static PlayerManager getInstance() {
        return instance;
    }

    public void addPlayer(KitPvPPlayer player) {
        players.add(player);
    }

    public void removePlayer(KitPvPPlayer player) {
        players.remove(player);
    }

    public ArrayList<KitPvPPlayer> getPlayers() {
        return players;
    }

    public KitPvPPlayer getPlayer(Player player) {
        for(KitPvPPlayer p : players) {
            if(p.getPlayer() == player) {
                return p;
            }
        }
        return null;
    }

    public Kit getPlayerKit(KitPvPPlayer player) {
        for(Kit kit : KitManager.getInstance().getKits()) {
            if(player.getKit() == kit) {
                return kit;
            }
        }
        return null;
    }
}

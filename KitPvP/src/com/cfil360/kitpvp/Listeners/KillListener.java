package com.cfil360.kitpvp.Listeners;

import com.cfil360.kitpvp.Managers.PlayerManager;
import com.cfil360.kitpvp.Util.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Connor on 4/19/2015.
 */
public class KillListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player player = e.getEntity();

        //if player isn't in kitpvp
        if(!PlayerManager.getInstance().getPlayers().contains(PlayerManager.getInstance().getPlayer(player))) return;

        if(player.getKiller() == null && !(player.getKiller() instanceof Player)) suicide(player);
        else murder(player);
    }

    public void suicide(Player player) {
        String tier = PlayerManager.getInstance().getPlayerKit(PlayerManager.getInstance().getPlayer(player)).getName();
        int kills = MySQL.getInstance().get(tier, player, "kills");
        int deaths = MySQL.getInstance().get(tier, player, "deaths") + 1;
        int wins = MySQL.getInstance().get(tier, player, "wins");
        int losses = MySQL.getInstance().get(tier, player, "losses") + 1;
        int rating = (int) (MySQL.getInstance().get(tier, player, "rating") * .95);
        MySQL.getInstance().update(player, tier, kills, deaths, wins, losses, rating);

        //remove the player from battle
        PlayerManager.getInstance().removePlayer(PlayerManager.getInstance().getPlayer(player));
    }

    public void murder(Player player) {
        Player killer = player.getKiller();

        //for the player
        String tier = PlayerManager.getInstance().getPlayerKit(PlayerManager.getInstance().getPlayer(player)).getName();
        int kills = MySQL.getInstance().get(tier, player, "kills");
        int deaths = MySQL.getInstance().get(tier, player, "deaths") + 1;
        int wins = MySQL.getInstance().get(tier, player, "wins");
        int losses = MySQL.getInstance().get(tier, player, "losses") + 1;
        int rating = MySQL.getInstance().get(tier, player, "rating") - MySQL.getInstance().calculateRatingChange(tier, player, killer);
        MySQL.getInstance().update(player, tier, kills, deaths, wins, losses, rating);

        //for the killer
        tier = PlayerManager.getInstance().getPlayerKit(PlayerManager.getInstance().getPlayer(player)).getName();
        kills = MySQL.getInstance().get(tier, player, "kills") + 1;
        deaths = MySQL.getInstance().get(tier, player, "deaths");
        wins = MySQL.getInstance().get(tier, player, "wins") + 1;
        losses = MySQL.getInstance().get(tier, player, "losses");
        rating = MySQL.getInstance().get(tier, player, "rating") + MySQL.getInstance().calculateRatingChange(tier, player, killer);
        MySQL.getInstance().update(player, tier, kills, deaths, wins, losses, rating);

        //remove the player from battle
        PlayerManager.getInstance().removePlayer(PlayerManager.getInstance().getPlayer(player));
        PlayerManager.getInstance().removePlayer(PlayerManager.getInstance().getPlayer(killer));
    }
}

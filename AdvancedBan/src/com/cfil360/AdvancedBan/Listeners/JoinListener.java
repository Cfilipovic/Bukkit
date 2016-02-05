package com.cfil360.AdvancedBan.Listeners;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Objects.Ban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

/**
 * Created by connor on 6/7/2014.
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        //return if there is no ban
        if(AdvancedBan.getMySQL().getBan(uuid) == null) return;

        Ban ban = AdvancedBan.getMySQL().getBan(uuid);

        if(ban.hasEnded()) return; //return if the ban has expired

        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, AdvancedBan.getMySQL().getBanMessage(AdvancedBan.getMySQL().getBan(uuid)));
    }
}

package com.cfil360.oneinthechamber.Listeners;

import com.cfil360.oneinthechamber.Arena.Arena;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Connor on 5/25/2015.
 */
public class DeathListener implements Listener {

    public void onDeath(PlayerDeathEvent event) {
        if(!Arena.isPlayer(event.getEntity())) return;

        Arena.getArena().removePlayer(event.getEntity());
    }
}

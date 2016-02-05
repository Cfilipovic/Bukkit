package com.cfil360.lazybitch.listeners;

import com.cfil360.lazybitch.LazyBitch;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * Created by Connor on 8/2/2015.
 */
public class CreeperListener implements Listener {

    boolean chance = getChance();

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        //make sure a creeper caused the damage
        if(!(event.getEntity() instanceof Creeper)) return;

        if(chance) {
            chance = getChance();
            event.setCancelled(true);
        }

        chance = getChance();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        //make sure a creeper caused the damage
        if(!(event.getDamager() instanceof Creeper)) return;

        //make sure a player was damaged
        if(!(event.getEntity() instanceof Player)) return;

        if(chance) event.setCancelled(true);
    }

    public boolean getChance() {
        double foo = Math.random() * 100;
        double chance = LazyBitch.getPlugin().getConfig().getDouble("Creeper_Fail");
        if (foo < chance) { // chance of the event being cancelled
            return true;
        } else return false;
    }
}

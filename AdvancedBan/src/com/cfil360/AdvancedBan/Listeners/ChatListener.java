package com.cfil360.AdvancedBan.Listeners;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Objects.Mute;
import com.cfil360.AdvancedBan.Util.DateUtil;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        //return if there is no mute
        if(AdvancedBan.getMySQL().getMute(uuid) == null) return;

        Mute mute = AdvancedBan.getMySQL().getMute(uuid);

        if(mute.hasEnded()) return; //return if the mute has expired

        MessageManager.getInstance().msg(event.getPlayer(), MessageManager.MessageType.BAD, "You are still muted for " + DateUtil.getMuteLength(AdvancedBan.getMySQL().getMute(uuid)));
        event.setCancelled(true);
    }
}

package com.cfil360.lobby;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 3/23/2015.
 */
public class Lobby extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJOin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        //display the titles for the player
        playTitle(player);

        //message the player about cool perks
        player.sendMessage("§6§l>> §3Welcome to §b§lExxtra Gaming!");
        player.sendMessage("§6§l>> §3Use your §eGame Selector §7§3or talk to an §eNPC §7§3to join a game!");
        player.sendMessage("§6§l>> §3Pick up §c§lPREMIUM §7§3from §b§nhttp://thenexusmc.com/shop§7§3 for perks like §ejoining full teams!");

        //make sure nothing else shows up
        e.setJoinMessage("");
    }

    public void playTitle(Player player) {
        // Title or subtitle, text, fade in (ticks), display time (ticks), fade out (ticks).
        PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"Exxtra Gaming\"}"), 20, 40, 80);
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"§3Website: §7§b§nwww.chub.com\"}"), 20, 40, 80);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);

        ParticleEff
    }
}


package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */
public class Kick {
    private java.util.UUID UUID;
    private String moderator;
    private Timestamp time ;
    private String reason;

    public Kick(UUID UUID, String moderator, Timestamp time, String reason) {
        this.UUID = UUID;
        this.moderator = moderator;
        this.time = time;
        this.reason = reason;
    }

    public void kick() {
        AdvancedBan.getMySQL().kick(this);
    }

    public UUID getUUID() {
        return this.UUID;
    }

    public String getModerator() {
        return this.moderator;
    }

    public Timestamp getTime() { return this.time; }

    public String getReason() {
        return this.reason;
    }

    public void successMessage(Kick kick) {
        //kick the player (might be offline)
        if(Bukkit.getPlayer(this.UUID) !=null) {
            Bukkit.getPlayer(this.UUID).kickPlayer(AdvancedBan.getMySQL().getKickMessage(this));
        }

        MessageManager.getInstance().broadcast(MessageManager.MessageType.INFO ,"§9" + Bukkit.getOfflinePlayer(kick.getUUID()).getName() + " §7has been kicked for §9" + kick.getReason());

    }
}

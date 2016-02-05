package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;

import java.util.Date;
import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */

public class Warn {
    private UUID UUID;
    private String moderator;
    private Date time ;
    private String reason;

    public Warn(UUID UUID, String moderator, Date time, String reason) {
        this.UUID = UUID;
        this.moderator = moderator;
        this.time = time;
        this.reason = reason;
    }

    public void warn() { AdvancedBan.mySQL.warn(this); }

    public java.util.UUID getUUID() {
        return this.UUID;
    }

    public String getModerator() {
        return this.moderator;
    }

    public Date getTime() { return this.time; }

    public String getReason() {
        return this.reason;
    }

    public void successMessage(Warn warn) {
        //warn the player (might be offline)
        if(Bukkit.getPlayer(this.UUID) !=null) {
            MessageManager.getInstance().msg(Bukkit.getPlayer(this.UUID), MessageManager.MessageType.BAD, AdvancedBan.getMySQL().getWarnMessage(this));
        }

        MessageManager.getInstance().broadcast(MessageManager.MessageType.INFO ,"§9" + Bukkit.getOfflinePlayer(warn.getUUID()).getName() + " §7has been warned for §9" + warn.getReason());
    }
}

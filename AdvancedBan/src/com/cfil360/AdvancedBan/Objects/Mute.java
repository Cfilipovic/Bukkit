package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.DateUtil;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */

public class Mute {
    private java.util.UUID UUID;
    private String moderator;
    private Timestamp expiration;
    private String reason;

    public Mute(UUID UUID, String moderator, Timestamp expiration, String reason) {
        this.UUID = UUID;
        this.moderator = moderator;
        this.expiration = expiration;
        this.reason = reason;
    }

    public void mute() {
        AdvancedBan.getMySQL().mute(this);
    }

    public java.util.UUID getUUID() {
        return this.UUID;
    }

    public String getModerator() {
        return this.moderator;
    }

    public Timestamp getExpiration() { return this.expiration; }

    public String getReason() {
        return this.reason;
    }

    public boolean hasEnded() {
        Timestamp expiration = this.getExpiration();
        DateUtil.formatDate().format(expiration);
        Timestamp current = new Timestamp(Calendar.getInstance().getTime().getTime());
        DateUtil.formatDate().format(current);

        //if the expiration is before the current date allow the player to join and delete the ban from the database
        if(expiration.before(current)) {
            return true;
        }
        else return false;
    }

    public void successMessage(Mute mute) {
        //mute the player (might be offline)
        if(Bukkit.getPlayer(this.UUID) !=null) {
            MessageManager.getInstance().msg(Bukkit.getPlayer(this.UUID), MessageManager.MessageType.BAD, AdvancedBan.getMySQL().getMuteMessage(this));
        }

        MessageManager.getInstance().broadcast(MessageManager.MessageType.INFO ,"§9" + Bukkit.getOfflinePlayer(mute.getUUID()).getName() + " §7has been muted for §9" + mute.getReason());
    }
}

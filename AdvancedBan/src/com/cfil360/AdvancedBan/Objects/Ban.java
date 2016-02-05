package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.DateUtil;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by connor on 6/5/2014.
 */

public class Ban {
    private UUID UUID;
    private String moderator;
    private Timestamp expiration ;
    private String reason;

    public Ban(UUID UUID, String moderator, Timestamp expiration, String reason) {
        this.UUID = UUID;
        this.moderator = moderator;
        this.expiration = expiration;
        this.reason = reason;
    }

    public void ban() {
        AdvancedBan.mySQL.ban(this);
    }

    public UUID getUUID() {
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

    public void successMessage(Ban ban) {
        //ban the player (might be offline)
        if(Bukkit.getPlayer(this.UUID) !=null) {
            Bukkit.getPlayer(this.UUID).kickPlayer(AdvancedBan.getMySQL().getBanMessage(this));
        }

        MessageManager.getInstance().broadcast(MessageManager.MessageType.INFO , "§9" + Bukkit.getOfflinePlayer(ban.getUUID()).getName() + " §7has been banned for §9" + DateUtil.getBanLength(ban) + " §7for §9" + ban.getReason());

    }
}

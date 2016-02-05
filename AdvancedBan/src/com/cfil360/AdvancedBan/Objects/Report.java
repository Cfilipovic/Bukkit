package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */

public class Report {
    private int ID;
    private String category;
    private UUID UUID;
    private String filedBy;
    private Timestamp happened;
    private String message;
    private String server;

    public Report(String category, UUID UUID, String filedBy, Timestamp happened, String message, String server) {
        this.ID = 0;
        this.category = category;
        this.UUID = UUID;
        this.filedBy = filedBy;
        this.happened = happened;
        this.message = message;
        this.server = server;
    }

    public void report() { AdvancedBan.mySQL.report(this); }

    public int getID() { return this.ID; }

    public String getCategory() { return this.category; }

    public java.util.UUID getUUID() {
        return this.UUID;
    }

    public String getFiledBy() {
        return this.filedBy;
    }

    public Timestamp getHappened() { return this.happened; }

    public String getMessage() {
        return this.message;
    }

    public String getServer() { return this.server; }

    public void successMessage(Report report) {
        //report the player (might be offline
        if(Bukkit.getPlayer(this.UUID) !=null) {
            MessageManager.getInstance().msg(Bukkit.getPlayer(this.UUID), MessageManager.MessageType.BAD, AdvancedBan.getMySQL().getReportMessage(this));
        }

        MessageManager.getInstance().msg(Bukkit.getPlayer(filedBy), MessageManager.MessageType.INFO ,"§9" + Bukkit.getOfflinePlayer(report.getUUID()).getName() + " §7has been reported for §9" + report.getMessage());
    }
}

package com.cfil360.uhc.Util;

import com.cfil360.gamecore.MySQLManager.MySQL;
import com.cfil360.uhc.UHC;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * Created by Connor on 5/4/2015.
 */
public class API {

    private static MySQL mySQL;
    private static Connection connection;
    public static int value;

    public static void setup(MySQL mySQL) {
        API.mySQL = mySQL;
        API.connection = mySQL.getConnection();
    }

    public static void getStat(final UUID uuid, final String stat) {
        Bukkit.getServer().getScheduler()
                .runTaskAsynchronously(UHC.getPlugin(), new Runnable() {
                    public void run() {
                        PreparedStatement statement;
                        try {
                            statement = connection.prepareStatement("SELECT " + stat + " FROM " + mySQL.getTable() + " WHERE uuid = '" + uuid + "'");
                            ResultSet res = statement.executeQuery();
                            res.next();
                            value = res.getInt(stat);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    }

    public static int getStatValue(UUID uuid, String stat) {
        getStat(uuid, stat);
        return value;
    }

    public static void setStat(final UUID uuid, final String stat, final int value) {
        Bukkit.getServer().getScheduler()
                .runTaskAsynchronously(UHC.getPlugin(), new Runnable() {
                    public void run() {
                        try {
                            PreparedStatement statement = connection.prepareStatement(
                                    "INSERT INTO " + mySQL.getTable() + " (uuid, kills, deaths, wins, losses, matchesPlayed) " +
                                            "VALUES " +
                                            "('" + uuid + "',0,0,0,0,0) " +
                                            "ON DUPLICATE KEY UPDATE " +
                                            stat + "=" + value + "");
                            statement.executeUpdate();
                            statement.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

    }

}

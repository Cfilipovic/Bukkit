package com.cfil360.chatlogger.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Created by connor on 7/11/2014.
 */
public class MySQL {

    private Connection connection;

    public MySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            Bukkit.broadcastMessage(ChatColor.RED + "Unable to connect to MySQL database!");
        }
    }

    public String getChat(Player p) {
        try {
            PreparedStatement statement = connection.prepareStatement("select message from chatlogger where uuid='" + p.getUniqueId() + "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getString("message");
            } else {
                return "Couldn't find message for " + p.getName();
            }
        } catch (Exception e) {
            return "Couldn't find message for " + p.getName();
        }
    }

    public void insertChat(Player p, String message) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into chatlogger (uuid, timeSent, message)\nvalues ('" + p.getUniqueId() +  "', '" + DateUtil.formatDate().format(Calendar.getInstance().getTime().getTime()) + "', '"  + message + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.cfil360.pointsapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.sql.*;
import java.util.UUID;

/**
 * Created by connor on 6/5/2014.
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

    public void clearPoints() {
        try {
            PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE points");
            ResultSet result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPoints(OfflinePlayer p) {
        try {
            PreparedStatement statement = connection.prepareStatement("select points from points where uuid='" + p.getUniqueId() + "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getInt("Points");
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public void insertPoints(UUID uuid, int points) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into points (uuid, points)\nvalues ('" + uuid + "', '" + points + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePoints(OfflinePlayer p, int points) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE points SET points ='" + points + "' where uuid='" + p.getUniqueId() + "'");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

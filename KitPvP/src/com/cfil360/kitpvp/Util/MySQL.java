package com.cfil360.kitpvp.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by connor on 6/5/2014.
 */
public class MySQL {

    private static MySQL instance = new MySQL("localhost", "root", "connor1", "Minecraft");

    public static MySQL getInstance() {
        return instance;
    }

    private Connection connection;

    public MySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            Bukkit.broadcastMessage(ChatColor.RED + "Unable to connect to MySQL database!");
        }
    }

    public void update(Player player, String tier, int kills, int deaths, int wins, int losses, int rating) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO leaderboard (uuid, tier, kills, deaths, wins, losses, rating) " +
                    "VALUES " +
                    "('" + player.getUniqueId() + "','" + tier + "','" + kills + "','" + deaths + "','" + wins + "','" + losses + "','" + rating + "') " +
                    "ON DUPLICATE KEY UPDATE " +
                    "uuid='" + player.getUniqueId() +
                    "', tier='" + tier +
                    "', kills='" + kills +
                    "', deaths='" + deaths +
                    "', wins='" + wins +
                    "', losses='" + losses +
                    "', rating='" + rating + "'");
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            Bukkit.getServer().broadcastMessage("error");
        }
    }

    public int get(String tier, Player player, String getType) {
        try {
            PreparedStatement statement = connection.prepareStatement("select " + getType + " from leaderboard where tier='" + tier + "' AND uuid='" + player.getUniqueId()+ "'");
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int product = result.getInt(getType);
                result.close();
                statement.close();
                return product;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    public int calculateRatingChange(String tier, Player player, Player killer) {
        int p1rating = get(tier, player, "rating");
        int p2rating = get(tier, killer, "rating");

        int difference = Math.abs(p1rating - p2rating);

        //return 15% of the difference btwn ranks
        return (int) (difference * .15);
    }

}

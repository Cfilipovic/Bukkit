package com.cfil360.shopapi.Util;

import com.cfil360.shopapi.Objects.Item;
import com.cfil360.shopapi.Objects.Purchase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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

    public ArrayList<Purchase> getPurchases(Player p) {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from purchases where uuid='" + p.getUniqueId() + "'");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                UUID uuid = UUID.fromString(result.getString("UUID"));
                String name = result.getString("Name");
                String item = result.getString("Item");
                int price = result.getInt("Price");
                Timestamp time = result.getTimestamp("Time");

                purchases.add(new Purchase(uuid, name, item, price, time));
            }
            result.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return purchases;
    }

    public void insertPurchase(Player p, String name, String item, int price) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into purchases (uuid, name, item, price, time)\nvalues ('" + p.getUniqueId() + "', '" + name + "', '" + item + "', '" + price + "', '" + DateUtil.formatDate().format(Calendar.getInstance().getTime().getTime()) + "');");
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from shop");
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                String name = result.getString("Name");
                int price = result.getInt("Price");
                String description = result.getString("Description");
                String type = result.getString("Type");
                String item = result.getString("Item");
                String id = result.getString("ID");

                items.add(new Item(name, price, description, type, item, id));    //create new item and add it to the list
            }
            result.close();
            statement.close();
        } catch (Exception e) {
            return null;
        }
        return items;
    }
}

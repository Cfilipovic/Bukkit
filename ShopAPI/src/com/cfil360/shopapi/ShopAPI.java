package com.cfil360.shopapi;

import com.cfil360.shopapi.Objects.Purchase;
import com.cfil360.shopapi.Util.InventoryHandler;
import com.cfil360.shopapi.Util.MySQL;
import com.cfil360.shopapi.Util.PurchaseInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by connor on 6/18/2014.
 */
public class ShopAPI extends JavaPlugin {
    public static MySQL mySQL;

    public void onEnable() {
        mySQL = new MySQL("localhost", "r", "pass", "db");
    }

    public static void insertPurchase(Player player, String name, String item, int price) {
        mySQL.insertPurchase(player, name, item, price);
    }

    public static Inventory getPurchaseInventory(Player player) { return PurchaseInventory.getPurchaseInventory(player); }

    public static ArrayList<Purchase> getPurchases(Player player) {
        return mySQL.getPurchases(player);
    }

    public static InventoryHandler getInventoryHandler() {
        return InventoryHandler.getInstance();
    }

}

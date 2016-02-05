package com.cfil360.shop;


import com.cfil360.shopapi.ShopAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by connor on 6/14/2014.
 */
public class Shop extends JavaPlugin {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);
    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("shop")) {
            Player player = (Player) sender;
            player.openInventory(ShopAPI.getInventoryHandler().getShopInventory());
        }

        if(command.getName().equalsIgnoreCase("purchases")) {
            Player player = (Player) sender;
            player.openInventory(ShopAPI.getPurchaseInventory(player));
        }
        return true;
    }
}


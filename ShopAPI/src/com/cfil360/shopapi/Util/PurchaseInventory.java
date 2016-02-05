package com.cfil360.shopapi.Util;

import com.cfil360.shopapi.Objects.Purchase;
import com.cfil360.shopapi.ShopAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by connor on 6/18/2014.
 */
public class PurchaseInventory {

    public static Inventory getPurchaseInventory(Player player) {
        Inventory inventory = Bukkit.getServer().createInventory(null, 27, "Purchase History");

        for(Purchase p : ShopAPI.getPurchases(player)) {
            ItemStack item = new ItemStack(Material.valueOf(p.getItem()));

            //name the item
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + p.getName());

            //add description to the item
            List<String> lore = new ArrayList<String>();

            lore.add("§7Purchased for §e" + p.getPrice() + " §7points on ");
            lore.add("§d" + DateUtil.formatDate().format(p.getTime()));

            //set the meta and lore for the item
            meta.setLore(lore);
            item.setItemMeta(meta);

            //add the item to the inventory
            inventory.addItem(item);
        }

        return inventory;
    }
}

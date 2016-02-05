package com.cfil360.shopapi.Util;

import com.cfil360.shopapi.Objects.Item;
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
 * Created by connor on 6/15/2014.
 */
public class InventoryHandler {
    static Item purchaseItem = null;

    private InventoryHandler() { }

    private static InventoryHandler instance = new InventoryHandler();

    public static InventoryHandler getInstance() {
        return instance;
    }

    public static void openInventory(Player player) {
        player.openInventory(getShopInventory());
    }

    public static Inventory getShopInventory() {
        Inventory inventory = Bukkit.getServer().createInventory(null, 27, "Shop");

        for(Item item : ShopAPI.mySQL.getItems()) {
            //create the item
            ItemStack i = new ItemStack(Material.valueOf(item.getItem()));

            //name the item
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName("§aBuy §e" + item.getName() + " §f" + item.getID());

            //add description to the item
            List<String> lore = new ArrayList<String>();

            lore.add("§7" + item.getDescription());
            lore.add(" ");
            lore.add("§7Price: §3" + item.getPrice() + " Credits");
            lore.add("§7Item Type: §6" + item.getType());
            lore.add(" ");
            lore.add("§l§6Left Click §7to purchase!");

            //set the meta and lore for the item
            meta.setLore(lore);
            i.setItemMeta(meta);

            //add the item to the inventory
            inventory.addItem(i);
        }

        return inventory;
    }

    public static Inventory getVerificationInventory() {
        Inventory inventory = Bukkit.getServer().createInventory(null, 45, "Verify Your Purchase");

        //create accept item and give it a custom name
        ItemStack accept = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
        ItemMeta acceptMeta = accept.getItemMeta();     acceptMeta.setDisplayName("§aAccept Purchase");     accept.setItemMeta(acceptMeta);

        inventory.setItem(0, accept);
        inventory.setItem(1, accept);
        inventory.setItem(9, accept);
        inventory.setItem(10, accept);
        inventory.setItem(18, accept);
        inventory.setItem(19, accept);
        inventory.setItem(27, accept);
        inventory.setItem(28, accept);
        inventory.setItem(36, accept);
        inventory.setItem(37, accept);

        Item item = purchaseItem;
        ItemStack i = new ItemStack(Material.valueOf(item.getItem()));

        //name the item
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName("§e" + item.getName() + " §f" + item.getID());

        //add description to the item
        List<String> lore = new ArrayList<String>();

        lore.add("§7Are you sure you wish to buy §e" + item.getName());
        lore.add("§7Price: §3" + item.getPrice() + " Credits");
        lore.add(" ");
        lore.add("§6Place in the green to verify purchase!");

        //set the meta and lore for the item
        meta.setLore(lore);
        i.setItemMeta(meta);

        //add the item to the inventory
        inventory.setItem(22, i);

        //create accept item and give it a custom name
        ItemStack decline = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta declineMeta = accept.getItemMeta();     declineMeta.setDisplayName("§cDecline Purchase");     decline.setItemMeta(declineMeta);

        inventory.setItem(7, decline);
        inventory.setItem(8, decline);
        inventory.setItem(16, decline);
        inventory.setItem(17, decline);
        inventory.setItem(25, decline);
        inventory.setItem(26, decline);
        inventory.setItem(34, decline);
        inventory.setItem(35, decline);
        inventory.setItem(43, decline);
        inventory.setItem(44, decline);

        return inventory;
    }

    public static void setPurchaseItem(Item item) {
        purchaseItem = item;
    }

    public static Item getPurchaseItem() {
        return purchaseItem;
    }

    public static ItemStack getPurchaseItemStack() {
        ItemStack item = new ItemStack(Material.valueOf(purchaseItem.getItem()));

        //name the item
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + purchaseItem.getName() + " §f" + purchaseItem.getID());

        //add description to the item
        List<String> lore = new ArrayList<String>();

        lore.add("§7" + purchaseItem.getDescription());
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

    public static boolean isStoreItem(ItemStack ItemStack) {
        //cycle through the items until the correct item is found
        for(Item item : ShopAPI.mySQL.getItems()) {
            if(ItemStack.getType().toString().equalsIgnoreCase(item.getItem())) {
                return true;
            }
        }
        return false;
    }

    public static Item getStoreItem(ItemStack itemStack) {
        //cycle through the items until the correct item is found
        for(Item item : ShopAPI.mySQL.getItems()) {
            if(itemStack.getType().toString().equalsIgnoreCase(item.getItem())) {
                return item;
            }
        }
        return null;
    }
}

package com.cfil360.shop;

import com.cfil360.pointsapi.PointsAPI;
import com.cfil360.shopapi.Objects.Item;
import com.cfil360.shopapi.ShopAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by connor on 6/15/2014.
 */
public class InventoryListener implements Listener {

    Plugin plugin;

    public InventoryListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if(!e.getInventory().getType().equals(InventoryType.CHEST)) return;
        if(e.getInventory().getName().equalsIgnoreCase(ShopAPI.getInventoryHandler().getShopInventory().getName())) {
            if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;   //return if the item is null or air

            //transfer to verification inventory
            e.getWhoClicked().closeInventory(); //close the player's inventory

            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    loadVerificationInventory(e.getWhoClicked(), e.getCurrentItem());
                    e.setCurrentItem(new ItemStack(Material.AIR));
                }
            }, 1L);
        }
        else if(e.getInventory().getName().equalsIgnoreCase(ShopAPI.getInventoryHandler().getVerificationInventory().getName())) {
            if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getCurrentItem().getItemMeta().getDisplayName() == null) return;  //return if null

            //cancel the click if it is an accept or decline glass pane
            if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Accept Purchase") || ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Decline Purchase")) {
                if(!ShopAPI.getInventoryHandler().isStoreItem(e.getCursor())) {
                    e.setCancelled(true);
                    return;
                }
            }

            Player player = (Player) e.getWhoClicked();

            //check to see if player has enough points
            if(PointsAPI.getPoints(player) < ShopAPI.getInventoryHandler().getPurchaseItem().getPrice()) {
                player.closeInventory();
                player.sendMessage("§cYou don't have enough points for that item.  §eCome back again when you have more points!");
                return;
            }

            //check location of item
            if(e.getSlot() == 0 || e.getSlot() == 1 || e.getSlot() == 9 || e.getSlot() == 10 || e.getSlot() == 18 || e.getSlot() == 19 || e.getSlot() == 27 || e.getSlot() == 28 || e.getSlot() == 36 || e.getSlot() == 37) {
                e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR)); //set item in hand to null
                e.setCancelled(true);               //cancel event
                e.getWhoClicked().closeInventory(); //close inv

                //subtract points from player
                PointsAPI.removePoints(player, ShopAPI.getInventoryHandler().getPurchaseItem().getPrice());

                //insert purhcase into the db
                ShopAPI.insertPurchase(player, ShopAPI.getInventoryHandler().getPurchaseItem().getName(), ShopAPI.getInventoryHandler().getPurchaseItem().getItem(), ShopAPI.getInventoryHandler().getPurchaseItem().getPrice());

                //add the item to the player's inv
                player.getInventory().addItem(ShopAPI.getInventoryHandler().getPurchaseItemStack());

                player.sendMessage("§aPurchase successful check your inventory!");
                //accepted purchase
            }
            else if(e.getSlot() == 7 || e.getSlot() == 8 || e.getSlot() == 16 || e.getSlot() == 17 || e.getSlot() == 25 || e.getSlot() == 26 || e.getSlot() == 34 || e.getSlot() == 35 || e.getSlot() == 43 || e.getSlot() == 44) {
                e.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR)); //set item in hand to null
                e.setCancelled(true);
                e.getWhoClicked().closeInventory();
                player.sendMessage("§cPurchase declined.  §eCome back again!");
                //decline purchase
            }
            else return;
        }
    }

    private void loadVerificationInventory(HumanEntity whoClicked, ItemStack i) {
        //cycle through the items until the correct item is found
        for(Item item : ShopAPI.mySQL.getItems()) {
            if(i.getType().toString().equalsIgnoreCase(item.getItem())) {
                ShopAPI.getInventoryHandler().setPurchaseItem(item);
                whoClicked.openInventory(ShopAPI.getInventoryHandler().getVerificationInventory());   //open the verification inventory
            }
        }
    }
}
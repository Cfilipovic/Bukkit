package com.cfil360.kitpvp.Listeners;

import com.cfil360.kitpvp.Kits.Global.KitPvPPlayer;
import com.cfil360.kitpvp.Kits.Kit;
import com.cfil360.kitpvp.Kits.KitManager;
import com.cfil360.kitpvp.Managers.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class KitSelection implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (e.getItem() == null || !e.getItem().hasItemMeta() || !e.getItem().getItemMeta().hasDisplayName()) {
            return;
        }

        //if the item is the kit selector
        if (ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equals("Kit Selector")) {
            e.getPlayer().openInventory(KitManager.getInstance().getGUI());
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        if (e.getCurrentItem() == null)return;
        Player p = (Player) e.getWhoClicked();

        //if the item is the back button
        if(e.getCurrentItem().getItemMeta() != null && ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equals("Back")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();

            p.openInventory(KitManager.getInstance().getGUI());
        }

        //do this if the item is the kit selector
        else if(e.getInventory().getName().equals(ChatColor.GOLD + "Kit Selector")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();

            //test for different type of clicks
            if (e.getClick().isLeftClick() || e.getClick().isRightClick()) {
                normalClick(e);
            } else if (e.getClick().isCreativeAction()) {
                middleClick(e);
            }
        }

        //do this if the item is a kit
        else if(KitManager.getInstance().getKits().contains(KitManager.getInstance().getKit(ChatColor.stripColor(e.getInventory().getName())))) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
        }
    }

    public void normalClick(InventoryClickEvent e) {
        //TODO:
        //Set the player's kit to the one they selected
        if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        PlayerManager.getInstance().addPlayer(new KitPvPPlayer((Player) e.getWhoClicked(), KitManager.getInstance().getKit(e.getCurrentItem().getItemMeta().getDisplayName())));
    }

    public void middleClick(InventoryClickEvent e) {
        //TODO:
        //Show the player the items in the kit
        Player player = (Player) e.getWhoClicked();
        player.closeInventory();
        Kit kit = KitManager.getInstance().getKit(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

        //open the kit's contents
        player.openInventory(kit.getInventory());
    }
}

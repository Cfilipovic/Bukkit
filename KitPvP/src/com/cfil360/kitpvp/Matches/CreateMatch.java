package com.cfil360.kitpvp.Matches;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Connor on 4/20/2015.
 */
public class CreateMatch {

    String type;
    String tier;
    Player player;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Inventory getCreateMatchGUI() {
        Inventory gui = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + "Ranked or Unranked");

        ItemStack ranked = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemMeta meta = ranked.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "RANKED");
        ranked.setItemMeta(meta);

        ItemStack unranked = new ItemStack(Material.WOOL, 1, (short) 9);
        meta = unranked.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "UNRANKED");
        unranked.setItemMeta(meta);

        //add the items to the inventory
        gui.addItem(ranked);
        gui.setItem(8, unranked);

        return gui;
    }

    public Inventory getCreateMatchTierSelect() {
        Inventory gui = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + "Select A Tier");

        ItemStack iron = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemStack gold = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemStack diamond = new ItemStack(Material.WOOL, 1, (short) 14);
        ItemStack archer = new ItemStack(Material.WOOL, 1, (short) 14);

        return gui;
    }
}

package com.cfil360.kitpvp.Kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Kit {

    private String name;
    private ArrayList<ItemStack> inventoryItems, armourItems;
    private ItemStack item;
    private Inventory inventory;

    public Kit(String name) {
        this.name = name;
        this.inventoryItems = new ArrayList<ItemStack>();
        this.armourItems = new ArrayList<ItemStack>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<ItemStack> getInventoryItems() {
        return (ArrayList<ItemStack>) inventoryItems.clone();
    }
    public ArrayList<ItemStack> getArmour() {
        return (ArrayList<ItemStack>) armourItems.clone();
    }

    public void addInventoryItem(Material material, int amnt, String name, String[] lore, SimpleEntry<Enchantment, Integer>... enchantments) {
        ItemStack item = new ItemStack(material, amnt);
        for (SimpleEntry<Enchantment, Integer> entry : enchantments) {
            item.addEnchantment(entry.getKey(), entry.getValue());
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        inventoryItems.add(item);
    }

    public void addArmourItem(Material material, int amnt, String name, String[] lore, SimpleEntry<Enchantment, Integer>... enchantments) {
        ItemStack item = new ItemStack(material, amnt);
        for (SimpleEntry<Enchantment, Integer> entry : enchantments) {
            item.addEnchantment(entry.getKey(), entry.getValue());
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        armourItems.add(item);
    }

    public void addSelectionItem(Material material, int amnt, String name) {
        ItemStack item = new ItemStack(material, amnt);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.YELLOW + "Middle Click To Preview The Kit");

        meta.setLore(lore);
        item.setItemMeta(meta);

        this.item = item;
    }

    public ItemStack getSelectionItem() {
        return item;
    }


    public Inventory getInventory() {
        inventory = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + name);

        //set the player's armor towards the top
        inventory.setItem(0, armourItems.get(0));
        inventory.setItem(1, armourItems.get(1));
        inventory.setItem(2, armourItems.get(2));
        inventory.setItem(3, armourItems.get(3));

        //set the player's weapons at the hotbar
        inventory.setItem(18, inventoryItems.get(0));

        //set the player's potions, food, other items
        //inventory.setItem(19, items.get(5));

        //set the back button
        inventory.setItem(26, getBackItem());

        return inventory;
    }

    public ItemStack getBackItem() {
        ItemStack item = new ItemStack(Material.WOOL);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");

        item.setItemMeta(meta);

        return item;
    }
}

package com.cfil360.kitpvp.Kits;

import com.cfil360.kitpvp.Kits.Archer.ArcherKit;
import com.cfil360.kitpvp.Kits.Diamond.DiamondKit;
import com.cfil360.kitpvp.Kits.Gold.GoldKit;
import com.cfil360.kitpvp.Kits.Iron.IronKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class KitManager {

    private static KitManager instance = new KitManager();

    public static KitManager getInstance() {
        return instance;
    }

    private ArrayList<Kit> kits;
    private Inventory gui;

    private KitManager() {
        kits = new ArrayList<Kit>();

        kits.add(new ArcherKit());
        kits.add(new DiamondKit());
        kits.add(new GoldKit());
        kits.add(new IronKit());
        // TODO: Add all other kits.

        gui = Bukkit.getServer().createInventory(null, InventoryType.CHEST, ChatColor.GOLD + "Kit Selector");

        for (Kit kit : kits) {
            gui.addItem(kit.getSelectionItem());
        }
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }

    public Kit getKit(String name) {
        for (Kit kit : kits) {
            if (kit.getName().equalsIgnoreCase(ChatColor.stripColor(name))) {
                return kit;
            }
        }

        return null;
    }

    public Inventory getGUI() {
        return gui;
    }

    public ItemStack getKitSelector() {
        ItemStack kitSelector = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = kitSelector.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Kit Selector");
        meta.setLore(Arrays.asList("Right click this", "to choose", "your kit."));
        kitSelector.setItemMeta(meta);

        return kitSelector;
    }
}

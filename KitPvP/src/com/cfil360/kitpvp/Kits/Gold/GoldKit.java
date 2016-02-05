package com.cfil360.kitpvp.Kits.Gold;

import com.cfil360.kitpvp.Kits.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.AbstractMap.SimpleEntry;

public class GoldKit extends Kit {

    public GoldKit() {
        super("Gold");

        addSelectionItem(Material.GOLD_CHESTPLATE, 1, "Gold");

        addArmourItem(Material.GOLD_HELMET, 1, "Gold Soldiers's Helmet", new String[0]);
        addArmourItem(Material.GOLD_CHESTPLATE, 1, "Gold Soldiers's Chestplate", new String[0]);
        addArmourItem(Material.GOLD_LEGGINGS, 1, "Gold Soldiers's Leggings", new String[0]);
        addArmourItem(Material.GOLD_BOOTS, 1, "Gold Soldiers's Boots", new String[0]);

        addInventoryItem(Material.GOLD_SWORD, 1, "Gold Soldiers's Bow", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.DAMAGE_ALL, 1));
    }
}
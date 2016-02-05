package com.cfil360.kitpvp.Kits.Archer;

import com.cfil360.kitpvp.Kits.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.AbstractMap.SimpleEntry;

public class ArcherKit extends Kit {

    public ArcherKit() {
        super("Archer");

        addSelectionItem(Material.BOW, 1, "Archer");

        addArmourItem(Material.LEATHER_HELMET, 1, "Archer's Helmet", new String[0]);
        addArmourItem(Material.LEATHER_CHESTPLATE, 1, "Archer's Chestplate", new String[0]);
        addArmourItem(Material.LEATHER_LEGGINGS, 1, "Archer's Leggings", new String[0]);
        addArmourItem(Material.LEATHER_BOOTS, 1, "Archer's Boots", new String[0]);

        addInventoryItem(Material.BOW, 1, "Archer's Bow", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.ARROW_INFINITE, 1));
        addInventoryItem(Material.ARROW, 64, "Archer's Arrows", new String[0]);
    }


}

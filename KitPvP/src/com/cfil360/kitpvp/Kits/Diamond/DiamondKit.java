package com.cfil360.kitpvp.Kits.Diamond;

import com.cfil360.kitpvp.Kits.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.AbstractMap.SimpleEntry;

public class DiamondKit extends Kit {

    public DiamondKit() {
        super("Diamond");

        addSelectionItem(Material.DIAMOND_CHESTPLATE, 1, "Diamond");

        addArmourItem(Material.DIAMOND_HELMET, 1, "Diamond Soldier's Helmet", new String[0]);
        addArmourItem(Material.DIAMOND_CHESTPLATE, 1, "Diamond Soldier's Chestplate", new String[0]);
        addArmourItem(Material.DIAMOND_LEGGINGS, 1, "Diamond Soldier's Leggings", new String[0]);
        addArmourItem(Material.DIAMOND_BOOTS, 1, "Diamond Soldier's Boots", new String[0]);

        addInventoryItem(Material.DIAMOND_SWORD, 1, "Diamond Soldier's Sword", new String[0], new SimpleEntry<Enchantment, Integer>(Enchantment.DAMAGE_ALL, 1));
    }
}

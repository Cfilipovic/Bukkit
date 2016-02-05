package com.cfil360.kitpvp.Kits.Iron;

    import  com.cfil360.kitpvp.Kits.Kit;
    import org.bukkit.Material;
    import org.bukkit.enchantments.Enchantment;

    import java.util.AbstractMap;

public class IronKit extends Kit {

    public IronKit() {
        super("Iron");

        addSelectionItem(Material.IRON_CHESTPLATE, 1, "Iron");

        addArmourItem(Material.IRON_HELMET, 1, "Iron Soldier's Helmet", new String[0]);
        addArmourItem(Material.IRON_CHESTPLATE, 1, "Iron Soldier's Chestplate", new String[0]);
        addArmourItem(Material.IRON_LEGGINGS, 1, "Iron Soldier's Leggings", new String[0]);
        addArmourItem(Material.IRON_BOOTS, 1, "Iron Soldier's Boots", new String[0]);

        addInventoryItem(Material.IRON_SWORD, 1, "Iron Soldier's Sword", new String[0], new AbstractMap.SimpleEntry<Enchantment, Integer>(Enchantment.DAMAGE_ALL, 1));
    }
}

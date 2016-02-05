package com.cfil360.kitpvp.Kits.Global;

import com.cfil360.kitpvp.Kits.Kit;
import com.cfil360.kitpvp.Kits.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Connor on 3/27/2015.
 */
public class KitPvPPlayer {

    private Kit kit;
    private Player player;

    public KitPvPPlayer(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;

        equip();
    }

    public Kit getKit() {
        return kit;
    }

    public Player getPlayer() {
        return player;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void equip() {
        player.getInventory().clear();

        player.getInventory().setHelmet(kit.getArmour().get(0));
        player.getInventory().setChestplate(kit.getArmour().get(1));
        player.getInventory().setLeggings(kit.getArmour().get(2));
        player.getInventory().setBoots(kit.getArmour().get(3));

        for(ItemStack item : kit.getInventoryItems()) {
            player.getInventory().addItem(item);
        }

        player.getInventory().setItem(8, KitManager.getInstance().getKitSelector());
    }
}

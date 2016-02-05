package com.cfil360.advancedkits.managers;

import com.cfil360.advancedkits.AdvancedKits;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Connor on 7/30/2015.
 */
public class SignListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getPlayer().hasPermission("kit.admin")) {
            if (e.getLine(0).equalsIgnoreCase("[AdvancedKits]")) {
                String kit = e.getLine(1);
                e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&e[&bAdvancedKits&e]"));
                e.setLine(1, ChatColor.translateAlternateColorCodes('&', "&fClick here"));
                e.setLine(2, ChatColor.translateAlternateColorCodes('&', "&fto equip"));
                if(AdvancedKits.getPlugin().getConfig().getBoolean("Economy") && KitManager.getInstance().isKit(kit)) {
                    e.setLine(3, ChatColor.translateAlternateColorCodes('&', "&2" + kit + "&e($" + KitManager.getInstance().getKit(kit).getPrice()) + ")");
                }
                else e.setLine(3, ChatColor.translateAlternateColorCodes('&', "&2" + kit));
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR) return;
        if (e.getClickedBlock().getState() instanceof Sign) {
            Sign s = (Sign) e.getClickedBlock().getState();
            if (ChatColor.stripColor(s.getLine(0)).equalsIgnoreCase("[AdvancedKits]")) {
                if(!KitManager.getInstance().isKit(ChatColor.stripColor(s.getLine(3)))) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Sign incorrectly configured!");
                    return;
                }

                if(!e.getPlayer().hasPermission("kit.player")) {
                    e.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to use kits!");
                    return;
                }

                if(AdvancedKits.getPlugin().getConfig().getBoolean("Economy")) {
                    EconomyResponse r = AdvancedKits.getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId()), KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))).getPrice());
                    if(AdvancedKits.getEconomy().getBalance(Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId()))  > KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))).getPrice() && r.transactionSuccess()) {
                        //give the player the kit
                        KitManager.getInstance().giveKit(e.getPlayer(), KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))));

                        e.getPlayer().sendMessage(ChatColor.AQUA + KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))).getName() + ChatColor.GREEN + " has been equipped.");

                        return;
                    } else {
                        e.getPlayer().sendMessage(ChatColor.RED + "You don't have enough money to purchase that kit!");
                        return;
                    }
                }

                KitManager.getInstance().giveKit(e.getPlayer(), KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))));

                e.getPlayer().sendMessage(ChatColor.AQUA + KitManager.getInstance().getKit(ChatColor.stripColor(s.getLine(3))).getName() + ChatColor.GREEN + " has been equipped.");

                return;
            }
        }
    }


}

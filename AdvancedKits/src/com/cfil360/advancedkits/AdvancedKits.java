package com.cfil360.advancedkits;

import com.cfil360.advancedkits.managers.Kit;
import com.cfil360.advancedkits.managers.KitManager;
import com.cfil360.advancedkits.managers.SignListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Connor on 7/28/2015.
 */
public class AdvancedKits extends JavaPlugin {
    public static Economy econ = null;

    public static Economy getEconomy() {
        return econ;
    }

    public void onEnable() {
        this.saveDefaultConfig();

        //load all the kits
        KitManager.getInstance().loadKits();

        Bukkit.getPluginManager().registerEvents(new SignListener(), this);

        if(getConfig().getBoolean("Economy")) {
            if (!setupEconomy() ) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Plugin disabled due to no vault dependency!");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("AdvancedKits");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("kit")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use AdvancedKits!");
                return true;
            }

            Player player = (Player) sender;

            if(args.length < 1) {
                player.sendMessage(ChatColor.YELLOW + "/kit list - " + ChatColor.AQUA + "Lists all available kits");
                player.sendMessage(ChatColor.YELLOW + "/kit [Kit Name] - " + ChatColor.AQUA + "Equips the requested kit");
                return true;
            }

            if(args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.AQUA + "Kits:");
                for(Kit kit : KitManager.getInstance().getKits()) {
                    player.sendMessage("    " + ChatColor.YELLOW + kit.getName());
                }

                return true;
            }

            if(!KitManager.getInstance().isKit(args[0])) {
                player.sendMessage(ChatColor.RED + "Not a valid kit!  Try /kit list");
                return true;
            }

            Kit kit = KitManager.getInstance().getKit(args[0]);

            if(!player.hasPermission("kit.player")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use kits!");
                return true;
            }

            if(getConfig().getBoolean("Economy")) {
                EconomyResponse r = econ.withdrawPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()), kit.getPrice());
                if(econ.getBalance(Bukkit.getOfflinePlayer(player.getUniqueId()))  > kit.getPrice() && r.transactionSuccess()) {
                    //give the player the kit
                    KitManager.getInstance().giveKit(player, kit);

                    player.sendMessage(ChatColor.AQUA + kit.getName() + ChatColor.GREEN + " has been equipped.");

                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money to purchase that kit!");
                    return true;
                }
            }

            //give the player the kit
            KitManager.getInstance().giveKit(player, kit);

            player.sendMessage(ChatColor.AQUA + kit.getName() + ChatColor.GREEN + " has been equipped.");

            return true;
        }
        return true;
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


}

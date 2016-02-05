package com.cfil360.advancedkits.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

/**
 * Created by Connor on 7/28/2015.
 */
public class KitManager {
    private KitManager() { }

    private static KitManager instance = new KitManager();

    public static KitManager getInstance() {
        return instance;
    }

    private ArrayList<Kit> kits = new ArrayList<Kit>();

    public void addKit(Kit kit) {
        kits.add(kit);
    }

    public Kit getKit(String name) {
        for(Kit kit : kits) {
            if(kit.getName().equalsIgnoreCase(name)) {
                return kit;
            }
        }

        //return null if not a valid kit
        return null;
    }

    public boolean isKit(String name) {
        if(getKit(name) != null) return true;
        else return false;
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }

    public void giveKit(Player player, Kit kit) {
        player.getInventory().clear();

        //remove potion effects
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        if(kit.getPotionEffect() != null)  {
            player.addPotionEffect(kit.getPotionEffect());
        }

        player.getInventory().setItem(0, kit.getWeapon());

        player.getInventory().setHelmet(kit.getHelmet());

        player.getInventory().setChestplate(kit.getChestplate());

        player.getInventory().setLeggings(kit.getLeggings());

        player.getInventory().setBoots(kit.getBoots());

        for(ItemStack item : kit.getOther()) {
            player.getInventory().addItem(item);
        }

        player.updateInventory();
    }

    public void loadKits() {
        ConfigurationSection config = SettingsManager.getKits().getConfig();
        for(String kit : config.getKeys(false)) {
            String name = kit;
            int price = config.getInt(kit + ".Price");

            PotionEffect potionEffect = null;

            try {
                if(config.getInt(kit + ".Potion_Effect.ID") > 0)
                potionEffect = getPotionEffect(config.getInt(kit + ".Potion_Effect.ID"), config.getInt(kit + ".Potion_Effect.Strength"), config.getInt(kit + ".Potion_Effect.Length"));
            } catch (Exception ex) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Potion effect incorrectly configured!");
            }

            ItemStack weapon = new ItemStack(config.getInt(kit + ".Weapon.ItemID"));
                ItemMeta weaponMeta = weapon.getItemMeta();
                weaponMeta.setDisplayName(config.getString(kit + ".Weapon.Name"));
                weapon.setItemMeta(weaponMeta);
                try {
                    if(config.getInt(kit + ".Weapon.Enchantment.ID") > 0)
                    weapon.addEnchantment(getEnchant(config.getInt(kit + ".Weapon.Enchantment.ID")), config.getInt(kit + ".Weapon.Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Weapon has an invalid enchantment applied!");
                }

            ItemStack helmet = new ItemStack(config.getInt(kit + ".Helmet.ItemID"));
                ItemMeta helmetMeta = helmet.getItemMeta();
                helmetMeta.setDisplayName(config.getString(kit + ".Helmet.Name"));
                helmet.setItemMeta(helmetMeta);
                try {
                    if(config.getInt(kit + ".Helmet.Enchantment.ID") >= 0)
                    helmet.addEnchantment(getEnchant(config.getInt(kit + ".Helmet.Enchantment.ID")), config.getInt(kit + ".Helmet.Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Helmet has an invalid enchantment applied!");
                }

            ItemStack chestplate = new ItemStack(config.getInt(kit + ".Chestplate.ItemID"));
                ItemMeta chestplateMeta = chestplate.getItemMeta();
                chestplateMeta.setDisplayName(config.getString(kit + ".Chestplate.Name"));
                chestplate.setItemMeta(chestplateMeta);
                try {
                    if(config.getInt(kit + ".Chestplate.Enchantment.ID") >= 0)
                    chestplate.addEnchantment(getEnchant(config.getInt(kit + ".Chestplate.Enchantment.ID")), config.getInt(kit + ".Chestplate.Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Chestplate has an invalid enchantment applied!");
                }

            ItemStack leggings = new ItemStack(config.getInt(kit + ".Leggings.ItemID"));
                ItemMeta leggingsMeta = chestplate.getItemMeta();
                leggingsMeta.setDisplayName(config.getString(kit + ".Leggings.Name"));
                leggings.setItemMeta(leggingsMeta);
                try {
                    if(config.getInt(kit + ".Leggings.Enchantment.ID") >= 0)
                    leggings.addEnchantment(getEnchant(config.getInt(kit + ".Leggings.Enchantment.ID")), config.getInt(kit + ".Leggings.Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Leggings have an invalid enchantment applied!");
                }

            ItemStack boots = new ItemStack(config.getInt(kit + ".Boots.ItemID"));
                ItemMeta bootMeta = boots.getItemMeta();
                bootMeta.setDisplayName(config.getString(kit + ".Boots.Name"));
                boots.setItemMeta(bootMeta);
                try {
                    if(config.getInt(kit + ".Boots.Enchantment.ID") >= 0)
                    boots.addEnchantment(getEnchant(config.getInt(kit + ".Boots.Enchantment.ID")), config.getInt(kit + ".Boots.Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Boots have an invalid enchantment applied!");
                }

            ArrayList<ItemStack> other = new ArrayList<>();

            for(int x = 1; x <= config.getConfigurationSection(kit + ".Other").getKeys(false).size(); x++) {
                ItemStack item = new ItemStack(config.getInt(kit + ".Other." + x + ".ItemID"), config.getInt(kit + ".Other." + x + ".Amount"));
                ItemMeta meta = item.getItemMeta();
                if(meta != null) {
                    meta.setDisplayName(config.getString(kit + ".Other." + x + ".Name"));
                    item.setItemMeta(meta);
                }
                try {
                    if(config.getInt(kit + ".Other." + x + ".Enchantment.ID") >= 0)
                        item.addEnchantment(getEnchant(config.getInt(kit + ".Other." + x + ".Enchantment.ID")), config.getInt(kit + ".Other." + x + ".Enchantment.Level"));
                } catch (Exception ex) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[" + ChatColor.AQUA + "AdvancedKits" + ChatColor.YELLOW + "] " + ChatColor.RED + "Other item #" + x + " has an invalid enchantment applied!");
                }

                other.add(item);
            }


            Kit k = new Kit(name, price, potionEffect, weapon, helmet, chestplate, leggings, boots, other);

            kits.add(k);
        }
    }

    public Enchantment getEnchant(int id) {
        switch (id) {
            case 0:
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case 1:
                return Enchantment.PROTECTION_FIRE;
            case 2:
                return Enchantment.PROTECTION_FALL;
            case 3:
                return Enchantment.PROTECTION_EXPLOSIONS;
            case 4:
                return Enchantment.PROTECTION_PROJECTILE;
            case 5:
                return Enchantment.OXYGEN;
            case 6:
                return Enchantment.WATER_WORKER;
            case 7:
                return Enchantment.THORNS;
            case 8:
                return Enchantment.DEPTH_STRIDER;
            case 16:
                return Enchantment.DAMAGE_ALL;
            case 17:
                return Enchantment.DAMAGE_UNDEAD;
            case 18:
                return Enchantment.DAMAGE_ARTHROPODS;
            case 19:
                return Enchantment.KNOCKBACK;
            case 20:
                return Enchantment.FIRE_ASPECT;
            case 21:
                return Enchantment.LOOT_BONUS_MOBS;
            case 32:
                return Enchantment.DIG_SPEED;
            case 33:
                return Enchantment.SILK_TOUCH;
            case 34:
                return Enchantment.DURABILITY;
            case 35:
                return Enchantment.LOOT_BONUS_BLOCKS;
            case 48:
                return Enchantment.ARROW_DAMAGE;
            case 49:
                return Enchantment.ARROW_KNOCKBACK;
            case 50:
                return Enchantment.ARROW_FIRE;
            case 51:
                return Enchantment.ARROW_INFINITE;
            case 61:
                return Enchantment.LUCK;
            case 62:
                return Enchantment.LURE;
        }
        return null;
    }

    public PotionEffect getPotionEffect(int id, int strength, int length) {
        switch (id) {
            case 1:
                return PotionEffectType.SPEED.createEffect(length * 20, strength);
            case 2:
                return PotionEffectType.SLOW.createEffect(length * 20, strength);
            case 3:
                return PotionEffectType.FAST_DIGGING.createEffect(length * 20, strength);
            case 4:
                return PotionEffectType.SLOW_DIGGING.createEffect(length * 20, strength);
            case 5:
                return PotionEffectType.INCREASE_DAMAGE.createEffect(length * 20, strength);
            case 6:
                return PotionEffectType.HEAL.createEffect(length * 20, strength);
            case 7:
                return PotionEffectType.HARM.createEffect(length * 20, strength);
            case 8:
                return PotionEffectType.JUMP.createEffect(length * 20, strength);
            case 9:
                return PotionEffectType.CONFUSION.createEffect(length * 20, strength);
            case 10:
                return PotionEffectType.REGENERATION.createEffect(length * 20, strength);
            case 11:
                return PotionEffectType.DAMAGE_RESISTANCE.createEffect(length * 20, strength);
            case 12:
                return PotionEffectType.WATER_BREATHING.createEffect(length * 20, strength);
            case 13:
                return PotionEffectType.INVISIBILITY.createEffect(length * 20, strength);
            case 14:
                return PotionEffectType.BLINDNESS.createEffect(length * 20, strength);
            case 15:
                return PotionEffectType.NIGHT_VISION.createEffect(length * 20, strength);
            case 16:
                return PotionEffectType.HUNGER.createEffect(length * 20, strength);
            case 17:
                return PotionEffectType.WEAKNESS.createEffect(length * 20, strength);
            case 18:
                return PotionEffectType.POISON.createEffect(length * 20, strength);
            case 19:
                return PotionEffectType.WITHER.createEffect(length * 20, strength);
            case 20:
                return PotionEffectType.HEALTH_BOOST.createEffect(length * 20, strength);
            case 21:
                return PotionEffectType.ABSORPTION.createEffect(length * 20, strength);
            case 22:
                return PotionEffectType.SATURATION.createEffect(length * 20, strength);
            case 23:
                return PotionEffectType.SLOW_DIGGING.createEffect(length * 20, strength);
        }
        return null;
    }
}


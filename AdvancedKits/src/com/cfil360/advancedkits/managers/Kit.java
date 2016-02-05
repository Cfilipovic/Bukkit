package com.cfil360.advancedkits.managers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;

public class Kit {

    private String name;
    private int price;
    private PotionEffect potionEffect;
    private ItemStack weapon;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ArrayList<ItemStack> other = new ArrayList<ItemStack>();

    public Kit(String name, int price, PotionEffect potionEffect, ItemStack weapon, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ArrayList<ItemStack> other) {
        this.name = name;
        this.price = price;
        this.potionEffect = potionEffect;
        this.weapon = weapon;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ArrayList<ItemStack> getOther() {
        return other;
    }
}

package com.cfil360.lazybitch.listeners;

import com.cfil360.lazybitch.LazyBitch;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Connor on 8/2/2015.
 */
public class EffectListener extends BukkitRunnable {

    private EffectListener() { }

    private static EffectListener instance = new EffectListener();

    public static EffectListener getInstance() {
        return instance;
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

    public boolean getChance() {
        double foo = Math.random() * 100;
        double chance = LazyBitch.getPlugin().getConfig().getDouble("Creeper_Fail");
        if (foo < chance) { // chance of the event being cancelled
            return true;
        } else return false;
    }

    @Override
    public void run() {

    }
}

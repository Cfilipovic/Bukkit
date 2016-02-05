package com.cfil360.oneinthechamber.Util;

import com.cfil360.oneinthechamber.Arena.Arena;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by Connor on 5/3/2015.
 */
public class Countdown extends BukkitRunnable {

    private Arena arena;
    private int i;
    private ArrayList<Integer> countingNums;

    public Countdown(Arena arena, int start, int... cNums) {
        this.arena = arena;
        this.i = start;
        this.countingNums = new ArrayList<Integer>();

        for (int c : cNums) {
            countingNums.add(c);
        }
    }

    @Override
    public void run() {
        if (i == 0) {
            arena.sound(Sound.WITHER_DEATH);
            arena.message("§eThe game has begun!");

            //new game start event
            arena.start();

            cancel();
            return;
        }

        if (countingNums.contains(i)) {
            arena.sound(Sound.NOTE_PLING);
            arena.message("§eThe game will begin in " + i + " seconds.");
        }

        i--;
    }
}

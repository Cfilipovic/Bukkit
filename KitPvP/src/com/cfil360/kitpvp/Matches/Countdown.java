package com.cfil360.kitpvp.Matches;

import com.cfil360.kitpvp.Managers.MessageManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Countdown extends BukkitRunnable {

    private Match match;
    private int i;
    private ArrayList<Integer> countingNums;

    public Countdown(Match match, int start, int... cNums) {
        this.match = match;
        this.i = start;
        this.countingNums = new ArrayList<Integer>();

        for (int c : cNums) {
            countingNums.add(c);
        }
    }

    @Override
    public void run() {
        if (i == 0) {
            MessageManager.getInstance().msg(match.getPlayer1(), MessageManager.MessageType.INFO, "The match has begun!");
            MessageManager.getInstance().msg(match.getPlayer2(), MessageManager.MessageType.INFO, "The match has begun!");

            match.start(); // If you want to generalize this class, you'd probably want a Runnable here.

            cancel();
            return;
        }

        if (countingNums.contains(i)) {
            MessageManager.getInstance().msg(match.getPlayer1(), MessageManager.MessageType.INFO, "The match will begin in " + i + " seconds.");
            MessageManager.getInstance().msg(match.getPlayer2(), MessageManager.MessageType.INFO, "The match will begin in " + i + " seconds.");
        }

        i--;
    }
}

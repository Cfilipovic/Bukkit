package com.cfil360.uhc.Executors;

import com.cfil360.gamecore.ArenaManager.Arena;
import com.cfil360.gamecore.CountdownManager.Countdown;
import com.cfil360.uhc.UHC;

/**
 * Created by Connor on 5/3/2015.
 */
public class StartGameExecutor extends com.cfil360.gamecore.GameExecutors.StartGameExecutor {
    Arena arena;

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void start() {
        //begin a countdown
        new Countdown(arena, 3, 30, 20, 15, 10, 5, 4, 3, 2, 1).runTaskTimerAsynchronously(UHC.getPlugin(), 0, 20);
    }
}

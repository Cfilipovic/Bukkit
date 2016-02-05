package com.cfil360.uhc.Executors;

import com.cfil360.gamecore.ArenaManager.Arena;

/**
 * Created by Connor on 5/3/2015.
 */
public class EndGameExecutor extends com.cfil360.gamecore.GameExecutors.EndGameExecutor {
    Arena arena;

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void end() {


    }
}

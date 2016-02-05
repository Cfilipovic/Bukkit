package com.cfil360.uhc.Commands;

import org.bukkit.entity.Player;

/**
 * Created by Connor on 4/26/2015.
 */
public abstract class Command {

    public abstract void onCommand(Player player, String[] args);

    private String message, usage;
    private String[] aliases;

    public Command(String message, String usage, String... aliases) {
        this.message = message;
        this.usage = usage;
        this.aliases = aliases;
    }

    public String getMessage() {
        return message;
    }

    public String getUsage() {
        return usage;
    }

    public String[] getAliases() {
        return aliases;
    }
}

package com.cfil360.AdvancedBan.Commands;

import org.bukkit.entity.Player;

/**
 * Created by connor on 7/2/2014.
 */
public abstract class SubCommand {

	public abstract void onCommand(Player p, String[] args);

	private String message, usage;
	private String[] aliases;

	public SubCommand(String message, String usage, String... aliases) {
		this.message = message;
		this.usage = usage;
		this.aliases = aliases;
	}

	public final String getMessage() {
		return message;
	}

	public final String getUsage() {
		return usage;
	}

	public final String[] getAliases() {
		return aliases;
	}
}

package com.cfil360.AdvancedBan.Util;

import com.cfil360.AdvancedBan.Commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by connor on 7/2/2014.
 */
public class CommandManager implements CommandExecutor {

	private ArrayList<SubCommand> cmds = new ArrayList<SubCommand>();

	public CommandManager() {
        cmds.add(new InfoCommand());
        cmds.add(new ReportCommand());
        cmds.add(new WarnCommand());
        cmds.add(new MuteCommand());
        cmds.add(new KickCommand());
        cmds.add(new BanCommand());
        cmds.add(new UnbanCommand());
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			MessageManager.getInstance().msg(sender, MessageManager.MessageType.BAD, "Only players can use admin!");
			return true;
		}

		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("admin")) {
			if (args.length == 0) {
				for (SubCommand sb : cmds) MessageManager.getInstance().msg(p, MessageManager.MessageType.INFO, "/admin " + aliases(sb) + " - " + sb.getMessage());
				return true;
			}

			SubCommand c = getCommand(args[0]);

			if (c == null) {
				MessageManager.getInstance().msg(sender, MessageManager.MessageType.BAD, "That command doesn't exist!");
				return true;
			}

			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);

			c.onCommand(p, args);

			return true;
		}
		return true;
	}

	private String aliases(SubCommand cmd) {
		String fin = "";

		for (String a : cmd.getAliases()) {
			fin += a + " | ";
		}

		return fin.substring(0, fin.lastIndexOf(" | "));
	}

	private SubCommand getCommand(String name) {
		for (SubCommand cmd : cmds) {
			if (cmd.getClass().getSimpleName().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.getAliases()) if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}
}
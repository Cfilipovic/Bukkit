package com.cfil360.uhc.Commands;

/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import com.cfil360.gamecore.Players.ExxtraPlayer;
import com.cfil360.uhc.UHC;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Connor on 4/26/2015.
 */
public class CommandManager implements CommandExecutor {
    private ArrayList<Command> commands = new ArrayList<Command>();

    public CommandManager() {
        commands.add(new Join());
    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("UHC")) {
            if (args.length == 0) {
                for (Command cm : commands) new ExxtraPlayer(player, UHC.getGame()).message(ExxtraPlayer.MsgType.INFO, "/uhc " + aliases(cm) + " - " + cm.getUsage());
                return true;
            }

            Command c = getCommand(args[0]);

            if (c == null) {
                new ExxtraPlayer(player, UHC.getGame()).message(ExxtraPlayer.MsgType.ERROR, "That command doesn't exist!");
                return true;
            }

            Vector<String> a = new Vector<String>(Arrays.asList(args));
            a.remove(0);
            args = a.toArray(new String[a.size()]);

            c.onCommand(player, args);

            return true;
        }
        return true;
    }

    private String aliases(Command cmd) {
        String fin = "";

        for (String a : cmd.getAliases()) {
            fin += a + " | ";
        }

        return fin.substring(0, fin.lastIndexOf(" | "));
    }

    private Command getCommand(String name) {
        for (Command cmd : commands) {
            if (cmd.getClass().getSimpleName().equalsIgnoreCase(name)) return cmd;
            for (String alias : cmd.getAliases()) if (name.equalsIgnoreCase(alias)) return cmd;
        }
        return null;
    }
}


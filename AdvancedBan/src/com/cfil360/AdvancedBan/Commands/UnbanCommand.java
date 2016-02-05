package com.cfil360.AdvancedBan.Commands;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by connor on 7/11/2014.
 */

public class UnbanCommand extends SubCommand {

    public UnbanCommand() {
        super("Unban a player", "/admin unban [player]", "unban");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        //make sure player has permission
        if(!p.hasPermission("Admin.Unban")) {
            MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "You don't have permisssion to unban players.");
            return;
        }

        //make sure all args are filled
        if(args.length < 1) {
             MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "Usage: " + this.getUsage());
            return;
        }

        Player moderator = p;                                                       //get the moderator
        OfflinePlayer offender = Bukkit.getOfflinePlayer(args[0]);                  //get the offender

        //remove the ban from the database
        AdvancedBan.getMySQL().deleteBan(offender.getUniqueId());

        MessageManager.getInstance().msg(p, MessageManager.MessageType.INFO,"§c" + args[0] + "'s ban has been removed!");
        return;
    }
}

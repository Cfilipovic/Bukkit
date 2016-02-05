package com.cfil360.AdvancedBan.Commands;

import com.cfil360.AdvancedBan.Objects.Info;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by connor on 7/11/2014.
 */

public class InfoCommand extends SubCommand {

    public InfoCommand() {
        super("Retrieve info on a player", "/admin info [player]", "info");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        //make sure player has permission
        if(!p.hasPermission("Admin.Info")) {
            MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "You don't have permisssion to get info on players.");
            return;
        }

        //make sure all args are filled
        if(args.length < 1) {
             MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "Usage: " + this.getUsage());
            return;
        }

        Player moderator = p;                                           //get the moderator
        OfflinePlayer offender = Bukkit.getOfflinePlayer(args[0]);      //get the offender

        Info info = new Info(offender.getUniqueId(), moderator);
    }
}

package com.cfil360.AdvancedBan.Commands;

import com.cfil360.AdvancedBan.Objects.Kick;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by connor on 7/11/2014.
 */
public class KickCommand extends SubCommand {

    public KickCommand() {
        super("Kick a player", "/admin kick [player] [reason]", "kick");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        //make sure player has permission
        if(!p.hasPermission("Admin.Kick")) {
            MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "You don't have permisssion to kick players.");
            return;
        }

        //make sure all args are filled
        if(args.length < 2) {
             MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "Usage: " + this.getUsage());
            return;
        }

        Player moderator = p;                                           //get the moderator
        OfflinePlayer offender = Bukkit.getOfflinePlayer(args[0]);      //get the offender

        //cycle through the remaining args and make 1 string called reason
            String reason = args[1];
            for(int x = 2; x < args.length; x++) {
                reason = reason + " " + args[x];
            }

        Kick kick = new Kick(offender.getUniqueId(), moderator.getName(), new Timestamp(Calendar.getInstance().getTime().getTime()), reason);
        kick.kick();
        kick.successMessage(kick);
    }
}

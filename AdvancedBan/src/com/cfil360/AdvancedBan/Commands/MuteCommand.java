package com.cfil360.AdvancedBan.Commands;

import com.cfil360.AdvancedBan.Objects.Mute;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by connor on 7/11/2014.
 */
public class MuteCommand extends SubCommand {

    public MuteCommand() {
        super("Mute a player", "/admin mute [player] [length] [mins/hours/days] [reason]", "mute");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        //make sure player has permission
        if(!p.hasPermission("Admin.Mute")) {
            MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "You don't have permisssion to mute players.");
            return;
        }

        //make sure all args are filled
        if(args.length < 4) {
            MessageManager.getInstance().msg(p, MessageManager.MessageType.BAD, "Usage: " + this.getUsage());
            return;
        }

        Player moderator = p;                                                       //get the moderator
        OfflinePlayer bannedPlayer = Bukkit.getOfflinePlayer(args[0]);              //get the offender
        int length = Integer.parseInt(args[1]);                                     //get the length of the ban
        String timeType = args[2];                                                  //get the type of time [mins/hours/days]

        //cycle through the remaining args and make 1 string called reason
        String reason = args[3];
        for(int x = 4; x < args.length; x++) {
            reason = reason + " " + args[x];
        }

        Mute mute = new Mute(bannedPlayer.getUniqueId(), moderator.getName(), getTimeStamp(timeType, length), reason);
        mute.mute();
        mute.successMessage(mute);
    }

    public Timestamp getTimeStamp(String timeType, int length) {
        Calendar calender = Calendar.getInstance(); //get the current system time

        if(timeType.equalsIgnoreCase("mins")) {
            calender.add(Calendar.MINUTE, length);      //add to the current system time in minutes
        }
        else if(timeType.equalsIgnoreCase("hours")) {
            calender.add(Calendar.HOUR, length);      //add to the current system time in hours
        }
        else if(timeType.equalsIgnoreCase("days")) {
            calender.add(Calendar.HOUR, length * 24);      //add to the current system time in days
        }
        else {
            //type = mins if not specified correctly
            calender.add(Calendar.MINUTE, length);      //add to the current system time in minutes
        }

        Date timestamp = calender.getTime();

        return new Timestamp(timestamp.getTime());
    }
}



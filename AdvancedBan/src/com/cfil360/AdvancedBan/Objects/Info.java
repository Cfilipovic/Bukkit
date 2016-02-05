package com.cfil360.AdvancedBan.Objects;

import com.cfil360.AdvancedBan.AdvancedBan;
import com.cfil360.AdvancedBan.Util.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by connor on 7/11/2014.
 */
public class Info {
    UUID uuid;
    Player sender;

    public Info(UUID uuid, Player sender) {
        this.uuid = uuid;
        this.sender = sender;

        //send the sender the info gathered
        infoMessage(this);
    }

    public String getUsername(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid).getName();
    }

    public int getReportCount(UUID uuid) {
       return AdvancedBan.getMySQL().getReportCount(uuid);
    }

    public int getWarnCount(UUID uuid) {
       return AdvancedBan.getMySQL().getWarningCount(uuid);
    }

    public int getMuteCount(UUID uuid) {
       return AdvancedBan.getMySQL().getMuteCount(uuid);
    }

    public int getKickCount(UUID uuid) {
       return AdvancedBan.getMySQL().getKickCount(uuid);
    }

    public int getBanCount(UUID uuid) {
       return AdvancedBan.getMySQL().getBanCount(uuid);
    }

    public void infoMessage(Info info) {
        MessageManager.getInstance().msg(sender, MessageManager.MessageType.INFO,
                "§6§l" + info.getUsername(uuid) + "\n§r" +
                "  §e§lReports:  §9" + getReportCount(uuid) + "\n§r" +
                "  §e§lWarnings:  §9" + getWarnCount(uuid) + "\n§r" +
                "  §e§lMutes:  §9" + getMuteCount(uuid) + "\n§r" +
                "  §e§lKicks:  §9" + getKickCount(uuid) + "\n§r" +
                "  §e§lBans:  §9" + getBanCount(uuid));
    }
}

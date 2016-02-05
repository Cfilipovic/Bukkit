package com.cfil360.uhc.Commands;

import com.cfil360.gamecore.Players.ExxtraPlayer;
import com.cfil360.uhc.UHC;
import org.bukkit.entity.Player;

/**
 * Created by Connor on 4/26/2015.
 */
public class Join extends Command {

    public Join() {
        super("Join a game", "/hg join [game]", "join");
    }

    @Override
    public void onCommand(Player player, String[] strings) {

        player.sendMessage(UHC.getGame().getName());
        ExxtraPlayer exxtraPlayer = new ExxtraPlayer(player, UHC.getGame());
        exxtraPlayer.message(ExxtraPlayer.MsgType.INFO, "yay");
    }
}

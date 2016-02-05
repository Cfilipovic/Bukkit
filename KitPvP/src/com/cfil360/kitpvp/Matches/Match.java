package com.cfil360.kitpvp.Matches;

import com.cfil360.kitpvp.Managers.MessageManager;
import com.cfil360.kitpvp.Util.Freezer;
import com.cfil360.kitpvp.Util.MySQL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 4/20/2015.
 */
public class Match {
    private Player player1;
    private Player player2;
    private String tier;
    private Arena arena;

    public Match(Player player1, String tier, Arena arena) {
        this.player1 = player1;
        this.tier = tier;
        this.arena = arena;
    }

    public Match(Player player1, Player player2, String tier, Arena arena) {
        this.player1 = player1;
        this.player2 = player2;
        this.tier = tier;
        this.arena = arena;
    }

    public void start() {
        //the match can no longer be joined
        MatchManager.getInstance().removeMatch(this);

        //remove the arena from open arenas
        ArenaManager.getInstance().removeArena(arena);

        //teleport players
        player1.teleport(arena.getSpawn1());
        player2.teleport(arena.getSpawn2());

        //freeze players
        Freezer.getInstance().freeze(player1);
        Freezer.getInstance().freeze(player2);

        //notify players of match begin
        String player1name = player1.getDisplayName() + "§b[" + MySQL.getInstance().get(tier, player1, "rating") + "]§r";
        String player2name = player2.getDisplayName() + "§b[" + MySQL.getInstance().get(tier, player2, "rating") + "]§r";
        MessageManager.getInstance().msg(player1, MessageManager.MessageType.INFO, player1name + " vs. " + player2name);
        MessageManager.getInstance().msg(player2, MessageManager.MessageType.INFO, player1name + " vs. " + player2name);

        //10 sec countdown
        Countdown c = new Countdown(this, 10, 10,9,8,7,6,5,4,3,2,1);
        c.run();

        //check for hits

        //when someone dies end the match
        //insert mysql data
    }

    public void stop() {
        MatchManager.getInstance().removeMatch(this);
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> pl = new ArrayList<Player>();
        pl.add(player1);
        pl.add(player2);

        return pl;
    }

    public Player getPlayer1() {return player1;}
    public Player getPlayer2() {return player2;}
    public String getTier() {return tier;}

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public ItemStack getSelectionItem() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player1.getName());
        meta.setDisplayName(ChatColor.YELLOW + tier);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.LIGHT_PURPLE + player1.getName() + " §b[" + MySQL.getInstance().get(tier, player1, "rating") + "]§r");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}

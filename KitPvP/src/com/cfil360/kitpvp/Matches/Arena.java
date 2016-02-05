package com.cfil360.kitpvp.Matches;

import org.bukkit.Location;

/**
 * Created by Connor on 4/20/2015.
 */
public class Arena {

    private Location spawn1;
    private Location spawn2;

    public Arena(Location spawn1, Location spawn2) {
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public void setSpawn1(Location spawn1) {
        this.spawn1 = spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }

    public void setSpawn2(Location spawn2) {
        this.spawn2 = spawn2;
    }
}

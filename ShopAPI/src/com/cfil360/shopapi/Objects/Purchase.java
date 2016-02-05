package com.cfil360.shopapi.Objects;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by connor on 6/18/2014.
 */
public class Purchase {

    UUID uuid;
    String name;
    String item;
    int price;
    Timestamp time;

    public Purchase(UUID uuid, String name, String item, int price, Timestamp time) {
        this.uuid = uuid;
        this.name = name;
        this.item = item;
        this.price = price;
        this.time = time;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() { return name; }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public Timestamp getTime() { return time; }
}

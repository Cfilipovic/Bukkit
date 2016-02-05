package com.cfil360.shopapi.Objects;

/**
 * Created by connor on 6/15/2014.
 */
public class Item {
    String Name;
    int Price;
    String Description;
    String Type;
    String Item;
    String ID;

    public Item(String Name, int Price, String Description, String Type, String Item, String ID) {
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
        this.Type = Type;
        this.Item = Item;
        this.ID = ID;
    }

    public String getName() { return this.Name; }

    public int getPrice() { return this.Price; }

    public String getDescription() { return this.Description; }

    public String getType() { return this.Type; }

    public String getItem() { return this.Item; }

    public String getID() { return this.ID; }

}
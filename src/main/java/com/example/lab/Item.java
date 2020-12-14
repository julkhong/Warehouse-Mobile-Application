package com.example.fit2081lab1;

public class Item {

    private String Name;
    private String Quantity;
    private String Price;
    private String Description;
    private String Frozen;

    public Item(String Name, String Quantity, String Price, String Description, String Frozen) {
        this.Name = Name;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Description = Description;
        this.Frozen = Frozen;
    }

    public String getName() {
        return Name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }
    public String getFrozen() {
        return Frozen;
    }
}
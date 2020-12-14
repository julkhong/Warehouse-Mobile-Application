package com.example.fit2081lab1.provider;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.example.fit2081lab1.provider.Task.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "Items";
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemID")
    private int itemID;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Quantity")
    private String quantity;

    @ColumnInfo(name = "Cost")
    private String cost;

    @ColumnInfo(name = "Description")
    private String descrip;

    @ColumnInfo(name = "Frozen")
    private String frozen;

    public Task(String name, String quantity, String cost, String descrip, String frozen) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.descrip = descrip;
        this.frozen = frozen;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCost() {
        return cost;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getFrozen() {
        return frozen;
    }


}

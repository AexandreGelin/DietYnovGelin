package com.ynov.dietynovgelin.models;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private int quantity;
    private String unit;
    private String iName;

    public Ingredient() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}

package com.ynov.dietynovgelin.models;

import java.io.Serializable;

public class Nutrition implements Serializable {
    private int kcal;
    private int protein;
    private int fat;
    private int carbohydrate;
    private int sugar;
    private int satFat;
    private int fiber;
    private int sodium;

    public Nutrition() {
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getSatFat() {
        return satFat;
    }

    public void setSatFat(int satFat) {
        this.satFat = satFat;
    }

    public int getFiber() {
        return fiber;
    }

    public void setFiber(int fiber) {
        this.fiber = fiber;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
}

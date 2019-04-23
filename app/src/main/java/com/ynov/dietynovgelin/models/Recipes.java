package com.ynov.dietynovgelin.models;

import java.io.Serializable;
import java.util.List;

public class Recipes implements Serializable {
    private String title;
    private int portions;
    private String rImage;
    private List<Ingredient> listIngredient;
    private List<Step> listStep;
    private Time time;
    Nutrition nutrition;

    public Recipes() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getrImage() {
        return rImage;
    }

    public void setrImage(String rImage) {
        this.rImage = rImage;
    }

    public List<Ingredient> getListIngredient() {
        return listIngredient;
    }

    public void setListIngredient(List<Ingredient> listIngredient) {
        this.listIngredient = listIngredient;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<Step> getListStep() {
        return listStep;
    }

    public void setListStep(List<Step> listStep) {
        this.listStep = listStep;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
}

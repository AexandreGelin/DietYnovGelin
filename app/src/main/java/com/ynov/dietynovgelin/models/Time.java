package com.ynov.dietynovgelin.models;

import java.io.Serializable;

public class Time implements Serializable {
    private int totaTime;
    private int prepTime;
    private int bakingTime;

    public Time() {
    }

    public int getTotaTime() {
        return totaTime;
    }

    public void setTotaTime(int totaTime) {
        this.totaTime = totaTime;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getBakingTime() {
        return bakingTime;
    }

    public void setBakingTime(int bakingTime) {
        this.bakingTime = bakingTime;
    }
}

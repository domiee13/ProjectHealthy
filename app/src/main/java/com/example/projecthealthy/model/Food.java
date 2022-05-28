package com.example.projecthealthy.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id, user_id;
    private float kcal;
    private String name, date;

    public Food() {
    }

    public Food(int user_id, float kcal, String name, String date) {
        this.user_id = user_id;
        this.kcal = kcal;
        this.name = name;
        this.date = date;
    }

    public Food(int id, int user_id, float kcal, String name, String date) {
        this.id = id;
        this.user_id = user_id;
        this.kcal = kcal;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

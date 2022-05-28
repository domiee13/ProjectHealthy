package com.example.projecthealthy.model;

import java.io.Serializable;

public class Step implements Serializable {
    private int id, user_id, count;
    private String date;
    private float kcal;

    public Step() {
    }

    public Step(int user_id, int count, String date, float kcal) {
        this.user_id = user_id;
        this.count = count;
        this.date = date;
        this.kcal = kcal;
    }

    public Step(int id, int user_id, int count, String date, float kcal) {
        this.id = id;
        this.user_id = user_id;
        this.count = count;
        this.date = date;
        this.kcal = kcal;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getKcal() {
        return kcal;
    }

    public void setKcal(float kcal) {
        this.kcal = kcal;
    }
}

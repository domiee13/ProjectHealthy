package com.example.projecthealthy.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String fullname, username, password;
    private int gender,age; // gender = 0 -> Male, gender = 1 -> Female
    private float height, weight;

    public User() {
    }

    public User(String fullname, String username, String password, int gender, int age, float height, float weight) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public User(int id, String fullname, String username, String password, int age, int gender, float height, float weight) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}

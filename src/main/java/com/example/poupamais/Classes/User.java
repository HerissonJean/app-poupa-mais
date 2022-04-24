package com.example.poupamais.Classes;

public class User {

    private String name;
    private String email;
    private String password;
    private String state;
    private int gender;

    public static final int MASCULINE = 1;
    public static final int FEMALE  = 2;

    public User(String name, String email, String password, String state,int gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.state = state;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}

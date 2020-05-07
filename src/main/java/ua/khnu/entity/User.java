package ua.khnu.entity;

import java.io.Serializable;


public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    private String number;
    private boolean isMailEnable;

    public User() {
    }

    public User(String email, String password, String number, boolean isMailEnable) {
        this.email = email;
        this.password = password;
        this.number = number;
        this.isMailEnable = isMailEnable;
    }

    public boolean isMailEnable() {
        return isMailEnable;
    }

    public void setMailEnable(boolean mailEnable) {
        isMailEnable = mailEnable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

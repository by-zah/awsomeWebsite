package ua.khnu.entity;

import ua.khnu.util.DBName;

import java.io.Serializable;


public class User implements Serializable {
    private int id;
    private String email;
    private String password;
    @DBName(name = "contactNumber")
    private String number;
    @DBName(name = "mailingEnabled")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", isMailEnable=" + isMailEnable +
                '}';
    }
}

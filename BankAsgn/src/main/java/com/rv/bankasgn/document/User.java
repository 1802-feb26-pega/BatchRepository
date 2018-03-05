package com.rv.bankasgn.document;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 507406308193736710L;
    private String firstname,
                   lastname,
                   email,
                   password;

    private float balance;

    public User() {
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.balance = 0.0f;
    }

    public User(String firstname, String lastname, String email, String pw) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = pw;
        this.balance = 0.0f;
    }

    public User(String firstname, String lastname, String email, String pw, float balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = pw;

        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return email  + "," + password + "," +firstname + "," + lastname + "," + balance;
    }
}

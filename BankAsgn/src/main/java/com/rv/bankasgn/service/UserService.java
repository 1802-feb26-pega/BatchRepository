package com.rv.bankasgn.service;

import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.access.UserAccessImplSerialize;
import com.rv.bankasgn.access.UserAccessImplText;
import com.rv.bankasgn.document.User;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserService {

    private UserAccess ua;
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    public UserService() {
        this.ua = new UserAccessImplText();
    }

    public UserService(UserAccess u) {
        this.ua = u;
    }

    public User getOneUser(String email, String pw) {
        User target = ua.getUser(email);

        if(target != null && pw.equals(target.getPassword()))
            return target;
        else
            return null;
    }

    public User saveUser(User u){
        ua.saveUser(u);
        return u;
    }

    public User registerUser(User u){
        if(ua.getUser(u.getEmail()) == null) {
            ua.saveUser(u);
        } else {
            return null;
        }

        return u;
    }

    public void backup(){
        ua.writeAll();
    }

}

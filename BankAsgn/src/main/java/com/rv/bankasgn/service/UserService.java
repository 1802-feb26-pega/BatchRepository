package com.rv.bankasgn.service;

import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.access.UserAccessImplRDB;
import com.rv.bankasgn.access.UserAccessImplSerialize;
import com.rv.bankasgn.pojos.User;

import java.text.NumberFormat;
import java.util.Locale;

public class UserService {

    private UserAccess ua;
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    public UserService() {
        this.ua = new UserAccessImplRDB();
    }

    public UserService(UserAccess u) {
        this.ua = u;
    }

    public User getOneUser(String email, String pw) {
        User target = ua.getUserByEmail(email);

        if(target != null && pw.equals(target.getPassword()))
            return target;
        else
            return null;
    }

    public User saveUser(User u){
        ua.saveUser(u);
        return u;
    }

    public void updateUser(User u) {
        ua.updateUser(u);
    }

    public User registerUser(User u){
        if(ua.getUserByEmail(u.getEmail()) == null) {
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

package com.rv.bankasgn.access;

import com.rv.bankasgn.document.User;

import java.io.FileNotFoundException;

public interface UserAccess {



    public User getUser(String email);
    public void saveUser(User u);
    public void writeAll();
    public void restore() throws FileNotFoundException;


}

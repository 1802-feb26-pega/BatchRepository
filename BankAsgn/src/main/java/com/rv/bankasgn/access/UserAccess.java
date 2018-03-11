package com.rv.bankasgn.access;

import com.rv.bankasgn.pojos.User;

import java.util.List;

public interface UserAccess {

    public List<User> getAllUsers();
    public User getUserByEmail(String email);
    public User getUserById(int id);
    public void saveUser(User u);
    public void updateUser(User u);
    public void writeAll();
}

package com.rv.bankasgn.access;

import com.rv.bankasgn.document.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccessImplSerialize implements UserAccess {

    public static final String FILE_ALL = "users.out";

    private List<User> allUsers;

    public UserAccessImplSerialize(){
        try {
            restore();
        } catch(FileNotFoundException f) {
            System.out.println("No backup found. Resorting to empty DB.");
            allUsers = new ArrayList<>();
        }
    }

    @Override
    public User getUser(String email) {
        for(User u : allUsers)
            if(u.getEmail().equals(email))
                return u;
        return null;
    }

    public void restore() throws FileNotFoundException{
        List<User> users = null;
        try(ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE_ALL))) {
            users = (List<User>) o.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found");
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        allUsers = users;
    }

    @Override
    public void writeAll() {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE_ALL))) {
            o.writeObject(allUsers);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveUser(User u) {
        if(allUsers.indexOf(u) == -1)
            allUsers.add(u);
        else
            allUsers.set(allUsers.indexOf(u), u);
    }

}

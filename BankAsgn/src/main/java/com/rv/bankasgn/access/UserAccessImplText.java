package com.rv.bankasgn.access;

import com.rv.bankasgn.document.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccessImplText implements UserAccess {

    public static final String FILE_ALL = "users.txt";

    private List<User> allUsers;

    public UserAccessImplText(){
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

    @Override
    public void writeAll() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_ALL, false))){
            for(User u : allUsers)
                bw.write(u.toString() + "\n");
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    private void restore() throws FileNotFoundException{
        List<User> sts = new ArrayList<>();

        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_ALL))) {

            while((line = br.readLine()) != null) {
                String[] tks = line.split(",");
                sts.add(new User(tks[2], tks[3], tks[0], tks[1], Float.parseFloat(tks[4])));
            }

        } catch(FileNotFoundException f) {
            System.out.println("Users file not found");
            throw f;
        } catch(IOException i) {
            i.printStackTrace();
        }
        allUsers = sts;
    }

    @Override
    public void saveUser(User u) {
        if(allUsers.indexOf(u) == -1)
            allUsers.add(u);
        else
            allUsers.set(allUsers.indexOf(u), u);
    }
}

package com.rv.bankasgn.access;

import com.rv.bankasgn.document.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccessImplText implements UserAccess {

    public static final String FILE_ALL = "data/users.txt",
                               FILEONE_DIR = "data/singles/";

    private List<User> allUsers;

    public UserAccessImplText(){
        allUsers = new ArrayList<>();
    }

    @Override
    public User getUser(String email) {
        return null;
    }

    @Override
    public void writeAll() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_ALL, false))){
            for(User u : allUsers)
                bw.write(u.toString());
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public void restore() {
        List<User> sts = new ArrayList<>();

        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_ALL))) {

            while((line = br.readLine()) != null) {
                String[] tks = line.split(",");
                sts.add(new User(tks[0], tks[1], tks[2], Float.parseFloat(tks[3])));
            }

        } catch(FileNotFoundException f) {

        } catch(IOException i) {

        }
        allUsers = sts;
    }

    @Override
    public void saveUser(User u) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILEONE_DIR + u.getFirstname() + ".txt", false))){

            bw.write(u.toString());

        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}

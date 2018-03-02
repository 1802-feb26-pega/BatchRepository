package com.rv.bankasgn.service;

import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.access.UserAccessImplSerialize;
import com.rv.bankasgn.document.User;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserService {

    private UserAccess ua;
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    public UserService() {
        this.ua = new UserAccessImplSerialize();
    }

    public UserService(UserAccess u) {
        this.ua = u;
    }

    public void attemptDeposit(Scanner s, User u){

        System.out.println("Okay, how many dollars?");
        try{
            float amount = 0.0f;
            int amountD = s.nextInt();
            s.nextLine();

            System.out.println("How many cents? (don't give more than 100)");
            int amountC = s.nextInt();
            s.nextLine();

            if(amountD >= 0 && (amountC >= 0 && amountC < 100)) {
                amount = amountD + ((float) amountC / 100);
                System.out.println("Depositing " + USD.format(amount));
                u.setBalance(u.getBalance()+amount);
            } else {
                System.err.println("One of your inputs was invalid. Try again later.");
            }
        } catch(InputMismatchException i){
            System.err.println("Oi, that ain't a valid form of currency! Please try again.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void attemptWithdrawal(Scanner s, User u) {

        System.out.println("Okay, how many dollars?");
        try{
            float amount = 0.0f;
            int amountD = s.nextInt();
            s.nextLine();

            System.out.println("How many cents? (don't give more than 99)");
            int amountC = s.nextInt();
            s.nextLine();

            if(amountD >= 0 && (amountC >= 0 && amountC < 100)) {
                amount = amountD + ((float) amountC / 100);
                System.out.println("Withdrawing " + USD.format(amount));
                if(amount <= u.getBalance())
                    u.setBalance(u.getBalance()-amount);
                else
                    System.out.println("You don't have that much money");
            }
            else
                System.out.println("Oi, you made a mistake with one of the inputs.");
        } catch(IllegalArgumentException i){
            System.err.println("Oi, that number doesn't resemble anything like currency! Please try again");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public User login(Scanner s) {
        User target = null;
        boolean finished = false;
        System.out.println("Okay, give us your email (or type \"cancel\" to exit): ");
        while(!finished) {
            String email = s.nextLine();

            if(email.equals("cancel")) {
                System.out.println("Not logging in anymore...");
                return null;
            }
            User x = ua.getUser(email);
            if(x == null) {
                System.out.println("Sorry, we don't have an account under that email, please enter another: ");
            } else {
                target = x;
                finished = true;
            }
        }

        return target;
    }

    public void logout(User u){
        ua.saveUser(u);
    }

    public User registerUser(Scanner s){
        User x = null;
        boolean finished = false;
        while(!finished) {
            System.out.println("Okay, give us an email you would like to use: ");
            String email = s.nextLine();

            System.out.println("Okay, give us your first name: ");
            String fname = s.nextLine();

            System.out.println("Okay, give us your last name: ");
            String lname = s.nextLine();

            System.out.println("Registered, now logging in.");
            finished = true;
            x = new User(fname,lname, email);
            ua.saveUser(x);
        }

        return x;
    }

    public void backup(){
        ua.writeAll();
    }

}

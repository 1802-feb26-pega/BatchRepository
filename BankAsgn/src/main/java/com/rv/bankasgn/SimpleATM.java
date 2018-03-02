package com.rv.bankasgn;

import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.document.User;
import com.rv.bankasgn.access.UserAccessImplSerialize;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SimpleATM {

    static final UserAccess UA = new UserAccessImplSerialize();
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    static void attemptDeposit(Scanner s, User u){

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
                System.out.println("Depositing $" + USD.format(amount));
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

    static void attemptWithdrawal(Scanner s, User u) {

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
                System.out.println("You don't have that much money... try again later");
        } catch(IllegalArgumentException i){
            System.err.println("Oi, that number doesn't resemble anything like currency! Please try again");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static User login(Scanner s) {
        User target = null;
        boolean finished = false;
        System.out.println("Okay, give us your email (or type \"cancel\" to exit): ");
        while(!finished) {
            String email = s.nextLine();

            if(email.equals("cancel")) {
                System.out.println("Not logging in anymore...");
                return null;
            }
            User x = UA.getUser(email);
            if(x == null) {
                System.out.println("Sorry, we don't have an account under that email, please enter another: ");
            } else {
                target = x;
                finished = true;
            }
        }

        return target;
    }


    static User registerUser(Scanner s){
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
            UA.saveUser(x);
        }

        return x;
    }

    public static void main(String... args) {
        User cur = null;

        Scanner s = new Scanner(System.in);
        boolean done = false;

        System.out.println("\nWelcome to the Absurdly Simplified Bank.\n");

        while(!done) {
            System.out.println("You are currently logged in as: " + (cur != null ? cur.getFirstname() + " " + cur.getLastname() : "not logged in"));
            System.out.println("Please enter a number from 1 to 7 to do one of the following: ");
            System.out.println("\t1: Create a new account\n" +
                               "\t2: Log into an existing account\n" +
                               "\t3: Log out of your current account\n" +
                               "\t4: Make a deposit\n" +
                               "\t5: Make a withdrawal\n" +
                               "\t6: View your current balance\n" +
                               "\t7: Exit the program");

            String choice = s.nextLine();

            switch(choice) {
                case "1":
                    if(cur != null)
                        System.out.println("Please log out before registering a new account");
                    else
                        cur = registerUser(s);
                    break;
                case "2":
                    if(cur != null)
                        System.out.println("You're already logged in!");
                    else
                        cur = login(s);
                    break;
                case "3":
                    UA.saveUser(cur);
                    cur = null;
                    break;
                case "4":
                    attemptDeposit(s, cur);
                    System.out.println("Your new balance is: " + USD.format(cur.getBalance()));
                    break;
                case "5":
                    attemptWithdrawal(s, cur);
                    System.out.println("Your new balance is: " + USD.format(cur.getBalance()));
                    break;
                case "6":
                    System.out.println("Your current balance is: " + USD.format(cur.getBalance()));
                    break;
                case "7":
                    done = true;
                    break;
                default:
                    System.out.println("Sorry, that choice is not valid.");
                    break;
            }
        }

        System.out.println("Ending program.");
        System.out.print("Saving user data...");
        UA.writeAll();
        System.out.print("done!\n");

    }
}

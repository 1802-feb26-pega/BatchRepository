package com.rv.bankasgn;

import com.rv.bankasgn.document.User;
import com.rv.bankasgn.service.UserService;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class SimpleATM {

    static final UserService USERVICE = new UserService();
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    public static void main(String... args) {
        User cur = null;

        Scanner s = new Scanner(System.in);
        boolean done = false;

        System.out.println("\nWelcome to the Absurdly Simplified Bank.\n");

        while(!done) {
            System.out.println("You are currently logged in as: " + (cur != null ? cur.getFirstname() + " " + cur.getLastname() : "[not logged in]"));
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
                        cur = USERVICE.registerUser(s);
                    break;
                case "2":
                    if(cur != null)
                        System.out.println("You're already logged in!");
                    else
                        cur = USERVICE.login(s);
                    break;
                case "3":
                    USERVICE.logout(cur);
                    cur = null;
                    break;
                case "4":
                    USERVICE.attemptDeposit(s, cur);
                    System.out.println("Your new balance is: " + USD.format(cur.getBalance()));
                    break;
                case "5":
                    USERVICE.attemptWithdrawal(s, cur);
                    System.out.println("Your new balance is: " + USD.format(cur.getBalance()));
                    break;
                case "6":
                    System.out.println("Your current balance is: " + USD.format(cur.getBalance()));
                    break;
                case "7":
                    USERVICE.logout(cur);
                    done = true;
                    break;
                default:
                    System.out.println("Sorry, that choice is not valid.");
                    break;
            }
        }

        System.out.println("Ending program.");
        System.out.print("Saving user data...");
        USERVICE.backup();
        System.out.print("done!\n");

    }
}

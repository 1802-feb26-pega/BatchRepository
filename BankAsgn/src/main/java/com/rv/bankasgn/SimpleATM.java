package com.rv.bankasgn;

import com.rv.bankasgn.document.User;
import com.rv.bankasgn.service.UserService;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SimpleATM {

    static final UserService USERVICE = new UserService();
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    private static User currentUser = null;
    private static final Scanner KB = new Scanner(System.in);

    static void create(){
        User x = null;
        boolean finished = false;
        System.out.println("Okay, give us an email you would like to use: ");
        String email = KB.nextLine();

        System.out.println("Okay, give us a password: ");
        String pw = KB.nextLine();

        System.out.println("Okay, give us your first name: ");
        String fname = KB.nextLine();

        System.out.println("Okay, give us your last name: ");
        String lname = KB.nextLine();

        x = new User(fname, lname, email, pw);

        while(!finished) {
            if (USERVICE.registerUser(x) != null) {
                System.out.println("Registered, now logging in.");
                currentUser = x;
                finished = true;
            } else {
                System.out.println("Sorry, that email is already in use, please enter another: ");
                email = KB.nextLine();
                x.setEmail(email);
            }
        }

    }
    static void login(){
        User target = null;
        boolean finished = false;
        while(!finished) {
            System.out.println("Okay, give us your email (or type \"cancel\" to exit): ");
            String email = KB.nextLine();

            System.out.println("Okay, now the password: ");
            String pw = KB.nextLine();

            if(email.equals("cancel")) {
                System.out.println("Not logging in anymore...");
                return;
            }
            User x = USERVICE.getOneUser(email, pw);
            if(x == null) {
                System.out.println("Sorry, one or both of your credentials are incorrect. Please try again.");
            } else {
                target = x;
                finished = true;
            }
        }

        currentUser = target;
    }

    static void logout() {
        USERVICE.saveUser(currentUser);
        currentUser = null;
    }

    static void depositOrWithdraw(boolean isDeposit) {
        System.out.println("Okay, how many dollars?");
        try{
            float amount = 0.0f;
            int amountD = KB.nextInt();
            KB.nextLine();

            System.out.println("How many cents? (don't give more than 100)");
            int amountC = KB.nextInt();
            KB.nextLine();

            if(amountD >= 0 && (amountC >= 0 && amountC < 100)) {
                amount = amountD + ((float) amountC / 100);
                if(isDeposit) {
                    System.out.println("Depositing " + USD.format(amount));
                    currentUser.setBalance(currentUser.getBalance() + amount);
                } else {
                    System.out.println("Withdrawing " + USD.format(amount));
                    currentUser.setBalance(currentUser.getBalance() - amount);
                }
                USERVICE.saveUser(currentUser);
            } else {
                System.err.println("One of your inputs was invalid. Try again later.");
            }
        } catch(InputMismatchException i){
            System.err.println("Oi, that ain't a valid form of currency! Please try again.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void viewBalance() {

    }

    public static void main(String... args) {
        boolean done = false;

        System.out.println("\nWelcome to the Absurdly Simplified Bank.\n");

        while(!done) {
            System.out.println("You are currently logged in as: " + (currentUser != null ? currentUser.getFirstname() + " " + currentUser.getLastname() : "[not logged in]"));
            System.out.println("Please enter a number from 1 to 7 to do one of the following: ");
            System.out.println("\t1: Create a new account\n" +
                               "\t2: Log into an existing account\n" +
                               "\t3: Log out of your current account\n" +
                               "\t4: Make a deposit\n" +
                               "\t5: Make a withdrawal\n" +
                               "\t6: View your current balance\n" +
                               "\t7: Exit the program");

            String choice = KB.nextLine();

            switch(choice) {
                case "1":
                    if(currentUser != null)
                        System.out.println("Please log out before registering a new account");
                    else
                        create();
                    break;
                case "2":
                    if(currentUser != null)
                        System.out.println("You're already logged in!");
                    else
                        login();
                    break;
                case "3":
                    logout();
                    break;
                case "4":
                    depositOrWithdraw(true);
                    System.out.println("Your new balance is: " + USD.format(currentUser.getBalance()));
                    break;
                case "5":
                    depositOrWithdraw(false);
                    System.out.println("Your new balance is: " + USD.format(currentUser.getBalance()));
                    break;
                case "6":
                    System.out.println("Your current balance is: " + USD.format(currentUser.getBalance()));
                    break;
                case "7":
                    logout();
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

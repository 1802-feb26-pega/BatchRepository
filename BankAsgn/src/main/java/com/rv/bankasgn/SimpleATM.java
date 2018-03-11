package com.rv.bankasgn;

import com.rv.bankasgn.pojos.Account;
import com.rv.bankasgn.pojos.User;
import com.rv.bankasgn.service.AccountService;
import com.rv.bankasgn.service.UserService;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SimpleATM {

    static final UserService USERVICE = new UserService();
    static final AccountService ASERVICE = new AccountService();
    static final NumberFormat USD = NumberFormat.getCurrencyInstance(Locale.US);

    private static User currentUser = null;
    private static List<Account> currentAccounts = null;
    private static final Scanner KB = new Scanner(System.in);

    static Account getOneAccount(int accountId) {
        for(Account a: currentAccounts)
            if(a.getAccountId() == accountId)
                return a;
        return null;
    }

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
                currentUser = USERVICE.getOneUser(x.getEmail(), x.getPassword());
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

            if(email.equals("cancel")) {
                System.out.println("Not logging in anymore...");
                return;
            }

            System.out.println("Okay, now the password: ");
            String pw = KB.nextLine();

            User x = USERVICE.getOneUser(email, pw);
            if(x == null) {
                System.out.println("Sorry, one or both of your credentials are incorrect. Please try again.");
            } else {
                target = x;
                currentAccounts = ASERVICE.getAccountsByUser(target.getUserId());
                finished = true;
            }
        }

        currentUser = target;
    }

    static void logout() {
        if(currentUser != null) {
            USERVICE.updateUser(currentUser);

            if(currentAccounts != null)
                for(Account a: currentAccounts)
                    ASERVICE.updateAccount(a);
        }
        currentUser = null;
        currentAccounts = null;
    }

    static void transfer() {
        Account from = null,
                to   = null;
        if(currentAccounts.size() < 2 || currentAccounts == null) {
            System.out.println("You need to open at least 2 accounts before you can do this.");
            return;
        }
        try{
            System.out.println("Okay, which of these accounts to you want to take funds from?");
            for(Account a : currentAccounts) {
                System.out.println("\t" + a.getAccountId());
            }
            int fromId = Integer.parseInt(KB.nextLine());

            System.out.println("Okay, which account will receive funds?");
            for(Account a : currentAccounts) {
                if(a.getAccountId() != fromId)
                    System.out.println("\t" + a.getAccountId());
            }
            int toId = Integer.parseInt(KB.nextLine());

            if(fromId == toId){
                System.out.println("Please don't initiate a transfer from and to the same account :(. Try again later.");
                return;
            }

            from = getOneAccount(fromId);
            to = getOneAccount(toId);

            System.out.println("Okay, how many dollars?");
            float amount = 0.0f;
            int amountD = Integer.parseInt(KB.nextLine());

            System.out.println("How many cents? (don't give more than 100)");
            int amountC = Integer.parseInt(KB.nextLine());

            if(amountD >= 0 && (amountC >= 0 && amountC < 100)) {
                amount = amountD + ((float) amountC / 100);
                if(amount <= from.getBalance()) {
                    System.out.println("Transferring " + USD.format(amount) + " from account " + fromId + " to account " + toId);
                    from.setBalance(from.getBalance() - amount);
                    to.setBalance(to.getBalance() + amount);
                } else {
                    System.out.println("You don't have that much money... try again.");
                    return;
                }
                ASERVICE.updateAccount(from);
                ASERVICE.updateAccount(to);
            } else {
                System.out.println("Oi, one of your inputs aren't valid. Try again later.");
            }
        } catch(Exception e) {
            System.out.println("Oi, something went wrong. Try again later.");
            return;
        }
    }

    static void depositOrWithdraw(boolean isDeposit) {

        if(currentAccounts == null || currentAccounts.size() < 1) {
            System.out.println("You need to open at least 1 account before you can do this.");
            return;
        }

        try{
            System.out.println("Alright, which account (enter the number) do you want to " + (isDeposit ? "deposit into?" : "withdraw from?"));
            System.out.println("Available accounts: ");
            for(Account a: currentAccounts){
                System.out.println("\t" + a.getAccountId());
            }
            int accountNumber = Integer.parseInt(KB.nextLine());
            Account target = getOneAccount(accountNumber);
            if(target == null) {
                System.out.println("Sorry, please give us a valid account number");
                return;
            }

            System.out.println("Okay, how many dollars?");
            float amount = 0.0f;
            int amountD = Integer.parseInt(KB.nextLine());

            System.out.println("How many cents? (don't give more than 100)");
            int amountC = Integer.parseInt(KB.nextLine());

            if(amountD >= 0 && (amountC >= 0 && amountC < 100)) {
                amount = amountD + ((float) amountC / 100);
                if(isDeposit) {
                    System.out.println("Depositing " + USD.format(amount));
                    target.setBalance(target.getBalance() + amount);
                } else {
                    if(amount <= target.getBalance()) {
                        System.out.println("Withdrawing " + USD.format(amount));
                        target.setBalance(target.getBalance() - amount);
                    } else
                        System.out.println("You don't have that much money...");
                }
                ASERVICE.updateAccount(target);
                System.out.println("Your new balance is: " + USD.format(target.getBalance()));
            } else {
                System.err.println("One of your inputs was invalid. Try again later.");
            }
        } catch(InputMismatchException i){
            System.err.println("Oi, that ain't a valid form of currency! Please try again.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        boolean done = false;
        System.out.println("\nWelcome to the Absurdly Simplified Bank.\n");

        while(!done) {
            System.out.println("You are currently logged in as: " + (currentUser != null ? currentUser.getFirstname() + " " + currentUser.getLastname() : "[not logged in]"));
            System.out.println("Please enter a number from 1 to 7 to do one of the following: ");
            System.out.println("\t1: Create a new user\n" +
                               "\t2: Log in as an existing user\n" +
                               "\t3: Log out as the current user\n" +
                               "\t4: Create a new bank account\n" +
                               "\t5: Make a deposit\n" +
                               "\t6: Make a withdrawal\n" +
                               "\t7: Transfer funds between your accounts\n" +
                               "\t8: View your current balance (for all of your accounts)\n" +
                               "\t9: Exit the program");

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
                    if(currentUser == null)
                        System.out.println("Please log in before you do that");
                    else
                        logout();
                    break;
                case "4":
                    if(currentUser != null) {
                        Account a = new Account();
                        a.setUserId(currentUser.getUserId());
                        ASERVICE.saveAccount(a);
                        currentAccounts = ASERVICE.getAccountsByUser(currentUser.getUserId());
                    } else
                        System.out.println("Please log in before you do that.");
                    break;
                case "5":
                    if(currentUser == null)
                        System.out.println("Please log in before you do that");
                    else
                        depositOrWithdraw(true);
                    break;
                case "6":
                    if(currentUser == null)
                        System.out.println("Please log in before you do that");
                    else
                        depositOrWithdraw(false);
                    break;
                case "7":
                    if(currentUser == null)
                        System.out.println("Please log in before you do that");
                    else
                        transfer();
                    break;
                case "8":
                    if(currentUser == null)
                        System.out.println("Please log in before you do that");
                    else
                        if(currentAccounts != null)
                            for(Account a: currentAccounts)
                                System.out.println(a.getAccountId() + ": " + USD.format(a.getBalance()));
                        else
                            System.out.println("You don't have any accounts yet!");
                    break;
                case "9":
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

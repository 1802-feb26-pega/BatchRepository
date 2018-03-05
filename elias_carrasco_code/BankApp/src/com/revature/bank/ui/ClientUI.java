package com.revature.bank.ui;

import java.util.Scanner;

import com.revature.bank.BankAccount;
import com.revature.bank.dao.*;
import com.revature.bank.service.ValidationService;

public class ClientUI implements Runnable {
        
        public ClientUI (){
                super();
        }
        
        @Override
        public void run() {
                Scanner sc = new Scanner(System.in);
                while(true) {
                String userChoice = "";
                System.out.println("1.) Open a new bank account");
                System.out.println("2.) Log in with an existing account");
                System.out.println("3.) Exit application");
                System.out.print("Pick an option by entering a number:");
                userChoice = sc.next();
                switch(userChoice) {
                case "1" :
                        createNewUser(sc);
                        break;
                case "2" : 
                        loginExistingUser(sc);
                        break;
                case "3" :
                        done();
                        sc.close();
                        return;
                default : 
                        System.out.println("Invlaid choice");
                        break; 
                } // end of switch
                }// end of while loop
        }
        
        private void loginExistingUser(Scanner sc) {

                ServiceToUI serv = new ServiceToUI();
                String email = "";
                String password = "";
                System.out.println();
                System.out.print("user: email      ->");
                email = sc.next();
                System.out.print("user: password   ->");
                password = sc.next();
                
                BankAccount existing = new BankAccount(email,password);
                BankAccount chk = serv.requestExistingUser(existing);
                
                if(chk.CheckInvalidState() ) {
                        System.out.println("failed to login");
                }
                else {
                        System.out.println("success!");
                        serviceExistingUser(chk, sc);
                }
                
        }

        private void serviceExistingUser(BankAccount ba, Scanner sc) {
                while(true) {
                        System.out.println("1.) View Balance");
                        System.out.println("2.) Make a deposit");
                        System.out.println("3.) Make a withdrawal");
                        System.out.println("4.) Log out");
                        System.out.print("Pick an option by entering a number:");
                        
                        String userChoice = sc.next();
                        switch(userChoice) {
                        case "1" : 
                                System.out.println("Current Balance : $" + 
                                                ba.getCurrentBalance());
                                break;
                        case "2" : 
                                ba = existingUserDeposit(ba,sc);
                                break;
                        case "3" : 
                                ba = existingUserWithdraw(ba,sc);
                                break;
                        case "4" : 
                                return;
                        default  :
                                System.out.println("Invalid choice");
                                break;
                        }
                }
        }

        private BankAccount existingUserWithdraw(BankAccount ba, Scanner sc) {
                
                ServiceToUI serv = new ServiceToUI();
                String amount;
                int withdraw = 0;
                System.out.print("Amount to withdraw :$");
                amount = sc.next();
                
                if(ValidationService.validNumber(amount)) {
                        withdraw = Integer.valueOf(amount);
                }
                else {
                        System.out.println("Invalid Number");
                        return ba;
                }
                
                BankAccount update = ba.copy();
                int curr = update.getCurrentBalance();
                update.setCurrentBalance(curr - withdraw);
                
                BankAccount temp = serv.requestEntryUpdateBalance(ba, update);
                if(temp.getCurrentBalance() == ba.getCurrentBalance()) {
                        System.out.println("Invalid amount to withdraw");
                        return ba;
                } else {
                        return temp;
                }
                
        }

        private BankAccount existingUserDeposit(BankAccount ba, Scanner sc) {
                ServiceToUI serv = new ServiceToUI();
                String amount;
                int deposit = 0;
                System.out.print("Amount to deposit :$");
                amount = sc.next();
                
                if(ValidationService.validNumber(amount)) {
                        deposit = Integer.valueOf(amount);
                }
                else {
                        System.out.println("Invalid Number");
                        return ba;
                }
                
                BankAccount update = ba.copy();
                int curr = update.getCurrentBalance();
                update.setCurrentBalance(curr + deposit);
                
                BankAccount temp = serv.requestEntryUpdateBalance(ba, update);
                if(temp.getCurrentBalance() == ba.getCurrentBalance()) {
                        System.out.println("Invalid amount to deposit");
                        return ba;
                } else {
                        return temp;
                }
                
        }

        private void createNewUser(Scanner sc) {

                String email = "timmyIsGreat@nowhere.gov";
                int balance = 0;
                String firstName = "Timmy";
                String lastName = "Rock";
                String password = "password";
                ServiceToUI serv = new ServiceToUI();

                System.out.print("new user: first name ->");
                firstName = sc.next();
                System.out.print("new user: last name  ->");
                lastName = sc.next();
                System.out.print("new user: email      ->");
                email = sc.next();
                System.out.print("new user: password   ->");
                password = sc.next();
                
                BankAccount newUser = new BankAccount(email,balance,firstName, lastName, password);
                newUser = serv.requestNewUser(newUser);
                
                if(newUser.CheckInvalidState()) {
                        System.out.println("Failed to create user. Check email and password");
                }
        }

        public void done() {
                ServiceToUI serv = new ServiceToUI();
                while(serv.cleanAndFinish() == false) {
                        try {
                                Thread.sleep(25);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                
        }


}

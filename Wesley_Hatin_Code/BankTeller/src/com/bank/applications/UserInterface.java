package com.bank.applications;

import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Teller t = new Teller();
		int option;
		Scanner menu = new Scanner(System.in);
		
		System.out.println("Welcome to your automated bank terminal!");
		System.out.println("If you would like to log in to your account, please press '1'.");
		System.out.println("If you would like to create a new account, please press '2'.");
		
		option = Integer.valueOf(menu.nextLine());
		System.out.println(""); 
		
		switch(option) {
		case 1:
			String user, pass;
			
			do {
				System.out.println("Enter your username: ");
				user = String.valueOf(menu.nextLine());
				System.out.println("");
				System.out.println("Enter your password: ");
				pass = String.valueOf(menu.nextLine());
				System.out.println("");
				if (t.logIn(user, pass)) {
					System.out.println("Success: Welcome, " + t.getName());
				} 
				else {
					System.out.println("Sorry, that's not a valid username/password combination.");
					System.out.println("Try again.");
					System.out.println("");
					pass = "";
				}
			} while (pass=="");
			break;
		case 2:
			t.createAccount();
			break;
		}
		String[] name = t.getName().split(" ");
		
		while(t.getUsername()!=null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name[0] + ", what action would you like to take now?");
			System.out.println("If you would like to make a deposit, please press '1'.");
			System.out.println("If you would like to make a withdrawl, please press '2'.");
			System.out.println("If you would like to check your balance, please press '3'.");
			System.out.println("If you would like to log out, please press '4'.");
			option=Integer.valueOf(menu.nextLine());
			System.out.println("");
			
			switch(option) {
			case 1:
				System.out.println("How much would you like to deposit?");
				t.deposit(Integer.valueOf(menu.nextLine()));
				
				System.out.println("");
				System.out.println("Your new balance is $" + t.getBalance());
				System.out.println("");
				break;
			case 2:
				System.out.println("How much would you like to withdraw?");
				
				if(t.withdraw(Integer.valueOf(menu.nextLine()))) {
					System.out.println("");
					System.out.println("Your new balance is $" + t.getBalance());
				}
				else {
					System.out.println("");
					System.out.println("You do not have enough funds to make that withdrawl.");
					System.out.println("We're nice though, so we won't fee you for it.");
				}
				System.out.println("");
				break;
			case 3:
				System.out.println("Your current balance is $" + t.getBalance());
				System.out.println("");
				break;
			case 4:
				String answer;
				System.out.println("Are you sure you want to log out?");
				System.out.println("Type Y or N: ");
				do {
					answer = menu.nextLine();
					if ((answer.toLowerCase()).equals("y")) {
						t.logOut();
					}
					else if((answer.toLowerCase()).equals("n")){
						System.out.println("Returning to menu...\n");
					} else {
						System.out.println("That was not a valid entry. Please type 'y' or 'n': ");

					} 
				}while (answer=="");
				break;
			}
		}
		System.out.println("Thank you for using your automated bank terminal.");
		System.out.println("Have a nice day!");
		menu.close();
	}

}

package com.bank.applications;

import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Teller t = new Teller();
		int option;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to your automated bank terminal!");
		System.out.println("If you would like to log in to your account, please press '1'.");
		System.out.println("If you would like to create a new account, please press '2'.");
		option = scan.nextInt();
		System.out.println(""); 
		scan.close();
		
		switch(option) {
		case 1:
			t.logIn();
			break;
		case 2:
			t.createAccount();
			break;
		}
		String[] name = t.getName().split(" ");
		Scanner menu = new Scanner(System.in);
		
		while(t.getUsername()!=null) {
			System.out.println(name[0] + ", what action would you like to take now?");
			System.out.println("If you would like to make a deposit, please press '1'.");
			System.out.println("If you would like to make a withdrawl, please press '2'.");
			System.out.println("If you would like to check your balance, please press '3'.");
			System.out.println("If you would like to log out, please press '4'.");
			option=menu.nextInt();
			System.out.println("");
			
			switch(option) {
			case 1:
				System.out.println("How much would you like to deposit?");
				t.deposit(scan.nextInt());
				System.out.println("");
				System.out.println("Your new balance is $" + t.getBalance());
				System.out.println("");
				break;
			case 2:
				System.out.println("How much would you like to withdraw?");
				if(t.withdraw(scan.nextInt())) {
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
				t.logOut();
				break;
			}
		}
		System.out.println("Thank you for using your automated bank terminal.");
		System.out.println("Have a nice day!");
		menu.close();
	}

}

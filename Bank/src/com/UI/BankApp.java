package com.UI;

import java.util.Scanner;

import com.io.UserIO;
import com.pojo.UserObject;

public class BankApp {

	Scanner in = new Scanner(System.in);
	//UserIO IO = new UserIO();
	
	public void bankAccount(UserObject user) {		
		System.out.println("Welcome back " + user.getUsername());
		
		boolean loop = true;
		while (loop) {
			System.out.println("Press 1 to make a deposit, 2 to withdraw, "
					+ "3 to view balance, or 0 to log out: ");
			
			int option = in.nextInt();
	
			switch(option) {
			case 0:
				loop = false;
				break;
			case 1:
				deposit(user);
				break;
			case 2:
				withdraw(user);
				break;
			case 3:
				System.out.println("Your balance is: " + user.getBalance());
				break;
			default:
				System.out.println("Error! Ivalid input");
				break;	
				
			} 	
		}
	}
	
	public void deposit(UserObject user) {
		System.out.println("How much money do you want to deposit?");
		int amount = in.nextInt();
		int prev = user.getBalance();
		user.setBalance((prev + amount));
		UserIO.update(user);
	}
	
	public void withdraw(UserObject user) {
		System.out.println("How much money do you want to withdraw?");
		int amount = in.nextInt();
		int prev = user.getBalance();
		System.out.println("current balance: " + prev);
		user.setBalance((prev - amount));
		UserIO.update(user);
	}
	
}

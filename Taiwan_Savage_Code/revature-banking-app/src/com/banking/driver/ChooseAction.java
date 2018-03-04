package com.banking.driver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.banking.user.User;

public class ChooseAction {
	
	/**
	 * 
	 * @param user
	 * Allows the user to choose what they would like to do within their account
	 */
	public ChooseAction(User user) {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.println("What would you like to do? Choose from below."
					+ "\n"
					+ "1) View Account Balance\n"
					+ "2) Deposit\n"
					+ "3) Withdraw\n"
					+ "4) Log Out\n");
			try {
				int choice = Integer.parseInt(scan.nextLine());
				clearConsole();
				if (choice < 1 || choice > 4) {
					System.out.println("Please choose a valid choice");
					clearConsole();
					continue;
				}
				else {
					if (choice == 1) {
						viewBalance(user);
					}
					else if (choice == 2) {
						deposit(user);
					}
					else if (choice == 3) {
						withdraw(user);
					}
					else {
						System.out.println("Have a nice day!");
						logout(user);
						break;
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Please choose a valid choice.");
				clearConsole();
				continue;
			}
		}
		return;
	}
	
	/**
	 * 
	 * @param user
	 * 
	 * prints user account balance
	 */
	public void viewBalance(User user) {
		System.out.println("Your current balance is: " + user.getAccountBalance());
		clearConsole();
		return;
	}
	
	/**
	 * 
	 * @param user
	 * 
	 * prompts the user to enter how much money they would like to deposit
	 * prints updated user account balance
	 */
	public void deposit(User user) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("How much would you like to deposit?");
			try {
				int amt = Integer.parseInt(scan.nextLine());
				clearConsole();
				if (amt < 0) {
					System.out.println("You can not deposit a negative amount, wise guy.");
					clearConsole();
					continue;
				}
				else if (amt > Integer.MAX_VALUE) {
					System.out.println("That is too large for a single deposit. Try a smaller amount.");
					clearConsole();
					continue;
				}
				else {
					user.setAccountBalance(user.getAccountBalance() + amt);
					break;
				}
				
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a number.");
				clearConsole();
				continue;
			}
		}
		 System.out.println("Updated balance is: " + user.getAccountBalance());
		 clearConsole();
		 return;
	}
	
	
	/**
	 * 
	 * @param user
	 * 
	 * prompts the user to enter how much money they would like to withdraw
	 * prints the updated user account balance
	 */
	public void withdraw(User user) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("How much would you like to withdraw?");
			try {
				int amt = Integer.parseInt(scan.nextLine());
				clearConsole();
				if (amt < 0) {
					System.out.println("You can not withdraw a negative amount, wise guy.");
					continue;
				}
				else if (amt > Integer.MAX_VALUE) {
					System.out.println("That is too large for a single withdrawal. Try a smaller amount.");
					clearConsole();
					continue;
				}
				else if (amt > user.getAccountBalance()) {
					System.out.println("Insufficient funds.");
					clearConsole();
					break;
				}
				else {
					user.setAccountBalance(user.getAccountBalance() - amt);
					break;
				}
				
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a number.");
				continue;
			}
		}
		System.out.println("Updated balance is: " + user.getAccountBalance());
		clearConsole();
		return;
	}
	
	/**
	 * log out method
	 * Serializes the user object.
	 */
	public void logout(User user) {
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(user.getSerializedFile()))) {
			oos.writeObject(user);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * clears the console for enhanced user experience
	 */
	public void clearConsole() {
		System.out.print("\n########################\n");
	}

}

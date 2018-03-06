package com.bank.service;

import java.io.IOException;
import java.util.Scanner;

public class Application {
	private int option;
	
	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public void run() {
		boolean invalidinput = true;
		do {
			try {
				System.out.println("To log in, enter 1");
				System.out.println("To create an account, enter 2");
				
				Scanner scan = new Scanner(System.in);
				setOption(scan.nextInt());
				scan.nextLine();
				if(getOption() != 1 && getOption() != 2) {
					//scan.close();
					throw new IOException();
				}
				
				invalidinput = false;
				//scan.close();
			} catch(IOException ime) {
				System.out.println("Invalid input; try again");
			}
			
			
		} while (invalidinput);
	
		if(getOption() == 1) {
			User login = new User();
			login.login();
		} else if (getOption()== 2) {
			User newacc = new User();
			newacc.makeAccount();
		}
	}
	
	
	public void mainMenu(User user) {
		boolean cont = true;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("\nTo view balance enter 1");
			System.out.println("To deposit money enter 2");
			System.out.println("To withdraw money enter 3");
			System.out.println("To log out enter 4");
			
			setOption(scan.nextInt());
			scan.nextLine();
			if (getOption() == 1) {
				System.out.println("\nYour current balance is: $" + user.getBalance());
				
			}
			else if (getOption() == 2) {
				System.out.println("Enter how much you would like to deposit: ");
				double withdraw = scan.nextDouble();
				double total = user.getBalance() + withdraw;
				user.setBalance(total);
				System.out.println("\nYour current balance is: $" + user.getBalance());
			}
			else if(getOption() == 3) {
				System.out.println("Enter how much you would like to withdraw: ");
				double withdraw = scan.nextDouble();
				double total = user.getBalance() - withdraw;
				user.setBalance(total);
				System.out.println("\nYour current balance is: $" + user.getBalance());
			}
			else if (getOption () == 4) {
				cont = false;
				System.out.println("\nLogged out.");
			} else {
				
			}
		} while (cont);
		
		user.updateData();
	}
}

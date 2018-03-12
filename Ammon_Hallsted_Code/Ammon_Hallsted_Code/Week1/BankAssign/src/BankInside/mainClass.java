package BankInside;

import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int value = 0;
		double amt = 0.0d;
		String line1;
		String line2;
		user newUser = new user();
		loginInfo logang = new loginInfo();
		
		do {
			System.out.println("Welcome! Have you been here before?\nPress 1 to create an account.\nPress 2 to login.");
			value = Integer.parseInt(scan.nextLine());
			switch (value) {
			case 1:
				//Make an account
				System.out.println("Please enter your email:");
				line2 = scan.nextLine();
				System.out.println("Please enter a username:");
				line1 = scan.nextLine();
				newUser.setUserEmail(line1, line2);
				System.out.println("Please enter a password:");
				line2 = scan.nextLine();
				newUser.setUserPass(line1,line2);
				newUser.setUserAcct(line1);
				System.out.println("We'll now log you out so you can log in.");
				logang.setLogin(false);
				break;
			case 2: 
				// Login
				System.out.println("Please enter your username:");
				line1 = scan.nextLine();
				System.out.println("Please enter your password:");
				line2 = scan.nextLine();
				if(true == newUser.boolUsername(line1) && true == newUser.boolPassword(line2)) {
					System.out.println("You've logged in!");
					logang.setLogin(true);
					while (logang.hasLogin() == true) {
						System.out.println("Would you like to:\n 1: Check your account. \n 2: Make a deposit \n 3: Make a Withdrawal \n 4: Logout");
						value = Integer.parseInt(scan.nextLine());
						switch (value) {
							case 1: 
								System.out.println(newUser.getAccount());
								break;
							case 2:
								System.out.println("What amount would you like to deposit?");
								amt = Double.parseDouble(scan.nextLine());
								newUser.increaseAccount(amt);
								System.out.println("Your new amount is: $"+newUser.getAccount());
								break;
							case 3:
								System.out.println("What amount would you like to deposit?");
								amt = Double.parseDouble(scan.nextLine());
								newUser.decreaseAccount(amt);
								System.out.println("Your new amount is: $"+newUser.getAccount());
								break;
							case 4:
								logang.setLogin(false);
								break;
							default:
								System.out.println("That was unreadable, please try again.");
						}
					}
				} else {System.out.println("Wrong input, please try again.");}
				break;
			default: 
				System.out.println("Incorrect Login, please run me again.");
				break;
			} scan.close();
			newUser.logoutRemoval();
		}
		while (logang.hasLogin() == false);
	}
}


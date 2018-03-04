package com.revature.projectI;

import java.util.ArrayList;
import java.util.Scanner;

public class BankMain {

	static Scanner scan = new Scanner(System.in);
	static Client customer = new Client();
	static Account account = new Account();
	static Validation v = new Validation();
	
	static boolean valid = false;

	public static void main(String[] args) {

		boolean loop = true;
		System.out.println("Hello welcome to Revature Banking!");
		System.out.println("Where we take care of all your banking needs.");

		while(loop) {
			System.out.println("Please read the following menu as our options may have changed:");
			System.out.println("If you would like to SIGN IN please PRESS 1");
			System.out.println("If you would like to SIGN UP please PRESS 2");
			System.out.println("If you you feel like you are in the wrong place and/or you would like to exit please PRESS 3");
			System.out.print("Input Here:");
			int choice = v.validateNum();//input validation needed

			switch(choice) {
			case 1: signin();break;
			case 2: signup(); break;
			case 3: 
				System.out.println("Thank for visiting Revature Banking Bye-bye");
				loop = false;
			default:
				System.out.println("Please enter in one of the options");
				System.out.println();
			}

		}
	}
	




private static void signup() {
	// TODO Auto-generated method stub

	ArrayList<String> info = new ArrayList<String>();

	//input valdation needed
	System.out.print("Please enter your First Name:");	
	String fn = scan.next();

	System.out.print("Please enter in your Last Name:");
	String ln = scan.next();

	System.out.print("Please enter in your SSN:"); //strip the dashes if used
	String ssn = v.validateNumSSN(scan.next());

	System.out.println();

	System.out.println("*Note: Please do not use your name or your SSN as apart of your User Name and Password.");
	System.out.print("Please enter in your desired User Name:");

	String usr = scan.next();

	System.out.println();
	System.out.print("Please enter in your desired password:");

	String pwd = scan.next();

	System.out.println("How much money will you like to put in initially?");
	String amount = Integer.toString(v.validateNum());

	System.out.println();
	System.out.println("Please review the following information:");
	System.out.println("First Name: " + fn);
	System.out.println("Last Name: " + ln);
	System.out.println("Social: " + ssn);
	System.out.println("User Name: " + usr);
	System.out.println("Password: " + pwd);
	System.out.println("Starting amount: "  + amount);

	System.out.print("Is it correct?");
	String checkInfo; 
	
	
	while(true) {
		
		checkInfo = scan.next();	
		if(checkInfo.equalsIgnoreCase("yes") |  checkInfo.equalsIgnoreCase("y")){	
			info.add(fn);
			info.add(ln);
			info.add(ssn);
			info.add(usr);
			info.add(pwd);
			info.add(amount);
			customer.addClient(info);
			break;
		}
		else if(checkInfo.equalsIgnoreCase("no") | checkInfo.equalsIgnoreCase("n")) {
			System.out.println();
			signup();
			break;
		}
		else {
			System.out.println("Please enter in Y or Yes for confirmation.");
			System.out.println("Please enter in N or No to restart.");
			System.out.print("Is it correct?");
			System.out.println();		
		}
			
	}
}

private static void signin() {
	
	boolean bankloop = true;
	
	// TODO Auto-generated method stub
	System.out.print("Please enter in your UserName:");
	String usrName = scan.next();

	System.out.print("Please enter in your Password:");
	String pwd = scan.next();

	
	System.out.println("");
	System.out.println("");
	System.out.println("");
	
	//Implement multiple login in attempts
	if (v.access(customer,account,usrName,pwd))		
		while(bankloop) 
			bankloop = startBanking();
}

private static boolean startBanking() {
	// TODO Auto-generated method stub
	System.out.println("What you would you like to do?");
	System.out.println("1. Check Balance");
	System.out.println("2. Withdraw");
	System.out.println("3. Deposit");
	System.out.println("4: Exit");
	System.out.print("Input here: ");
	int option = scan.nextInt();
	System.out.println("");

	switch(option) {
	case 1:
		account.checkBal();
		break;
	case 2:
		account.checkBal();
		System.out.println("How much would you like to withdraw?");	
		System.out.print("Input here: ");
		int w = scan.nextInt();
		account.withdraw(w,customer);
		break;
	case 3:
		account.checkBal();
		System.out.println("How much would you like to deposit?");	
		System.out.print("Input here: ");
		int d = scan.nextInt();
		account.deposit(d,customer);
		break;
	case 4: 
		System.out.println("");
		System.out.println("");
		System.out.println("");
		return false;
	}
	return true;
}
}

package com.revature.banking.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.banking.dao.Account_Dao;
import com.revature.banking.dao.Client_Dao;
import com.revature.banking.dao.DaoImpl;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.Validation;

public class BankDriver {

	static Scanner scan = new Scanner(System.in);
	static Account account;
	static Client customer;
	static int tries = 0;
	static boolean exit = false;

	static boolean valid = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean loop = true;
		int choice = 0;
		System.out.println("Hello welcome to Revature Banking!");
		System.out.println("Where we take care of all your banking needs.");

		while(loop) {
			System.out.println("Please read the following menu as our options may have changed:");
			System.out.println("If you would like to SIGN IN please PRESS 1");
			System.out.println("If you would like to SIGN UP please PRESS 2");
			System.out.println("If you you feel like you are in the wrong place and/or you would like to exit please PRESS 3");
			System.out.print("Input Here:");
			choice = Validation.validateNum(scan.next());//input validation needed

			switch(choice) {
			case 1: signin();break;
			case 2: signup(); break;
			case 3: 
				System.out.println("Thank for visiting Revature Banking Bye-bye");
				loop = false;
				break;
			default:
				System.out.println("Please enter in one of the options");
				System.out.println();
			}

		}
	}





	private static void signup() {
		// TODO Auto-generated method stub

		System.out.print("Please enter your First Name:");	
		String fn = scan.next();

		System.out.print("Please enter in your Last Name:");
		String ln = scan.next();

		System.out.print("Please enter in your SSN:"); //strip the dashes if used
		int ssn = Validation.validateNumSSN(scan.next());

		System.out.println();

		System.out.println("*Note: Please do not use your name or your SSN as apart of your User Name and Password.");
		System.out.print("Please enter in your desired User Name:");

		String usr = scan.next();

		System.out.println();
		System.out.print("Please enter in your desired password:");

		String pwd = scan.next();

		System.out.println("How much money will you like to put in initially?");
		int amount = Validation.validateNum(scan.next());


		System.out.println("We have added a couple of more option to our application");
		System.out.println("Please read the following menu as our options may have changed:");
		System.out.println("If you want a Checking account PRESS 1");
		System.out.println("If you want a Credit account PRESS 2");
		System.out.println("If you want a Saving account PRESS 3");
		System.out.println("If you want a Loans account PRESS 4");
		System.out.println("What type of account do you want?");
		int typeof = user_type(Validation.validateNum(scan.next()));

		
		String checkInfo; 
		String type = null;


		while(true) {

			System.out.println();
			System.out.println("Please review the following information:");
			System.out.println("First Name: " + fn);
			System.out.println("Last Name: " + ln);
			System.out.println("Social: " + ssn);
			System.out.println("User Name: " + usr);
			System.out.println("Password: " + pwd);
			System.out.println("Starting amount: "  + amount);
			
			switch(typeof) {
			case 1:  type = "Checking";break;
			case 2:  type = "Credit"; break;
			case 3:  type = "Saving";break;
			case 4:  type = "Loans";break;
			}
			
			System.out.println("Type of account : " + type);


			System.out.println("Is it correct?");
			System.out.print("Input here:");
			
			checkInfo = scan.next();
			System.out.println();
			if(checkInfo.equalsIgnoreCase("yes") |  checkInfo.equalsIgnoreCase("y")){	
				customer = new Client(fn,ln,ssn,usr,pwd);				
				account = new Account(typeof, amount, null);

				if(Validation.writeNewUser(customer, account)) {
					System.out.println(fn + " " + ln + "  has been added successfully");
					System.out.println("Thank you for signing up with Revature bank!");
					System.out.println();
					System.out.println();
				}
				else {
					System.out.println("Problems have occured please try again.");
				}

				break;
			}
			else if(checkInfo.equalsIgnoreCase("no") | checkInfo.equalsIgnoreCase("n")) {
				System.out.println();
				signup();//Form of recusrion
				break;
			}
			else {
				System.out.println("Please enter in Y or Yes for confirmation.");
				System.out.println("Please enter in N or No to restart.");
				System.out.println();		
			}

		}
	}

	public static int user_type(int wants) {
		int temp = 0;


		switch(wants) {
		case 1: temp = 1; break;//"Checking";
		case 2: temp = 2; break;//"Credit"; 
		case 3: temp = 3; break;//"Saving"; 
		case 4: temp = 4; break;// "Loans";
		}
		return temp;

	}

	private static void signin() {

		boolean bankloop = true;
		
		System.out.print("Please enter in your UserName:");
		String usrName = scan.next();

		System.out.print("Please enter in your Password:");
		String pwd = scan.next();


		System.out.println("");
		System.out.println("");
		System.out.println("");

		customer = new Client();
		account = new Account();
		//Implement multiple login in attempts
		if (Validation.access(customer,account,usrName,pwd))
			while(bankloop) 
				bankloop = startBanking();
		else {
			System.out.println("Access denied");
			System.out.println("Password and/or Username was incorrect");
			System.out.println();
			if(tries < 3) {
				tries++; signin();
			}
			else {
				System.out.println();
				System.out.println();
				System.out.println("Too many attempts returning to main menu");
				System.out.println();
				System.out.println();
			}
		}
		
		System.out.println("If you would like to return to the menu please PRESS 1");
		System.out.println("OTHERWISE PLEASE ENTER ANY OTHER NUMBER");
		
		System.out.print("Input Here:");
		int option = Validation.validateNum(scan.next());

		// TODO Auto-generated method stub
		if(option==1) return;
		
	}

	private static boolean startBanking() {
		// TODO Auto-generated method stub
		System.out.println("Welcome " + customer.getfName() + " " + customer.getlName());
		System.out.println("What you would you like to do?");
		System.out.println("PRESS 1 to Check Balance");
		System.out.println("PRESS 2 to Withdraw");
		System.out.println("PRESS 3 to Deposit");
		System.out.println("PRESS 4 to Add An Account");
		System.out.println("PRESS 5 to View Accounts");
		System.out.println("PRESS 0 to Exit");
		System.out.print("Input here: ");
		int option = Validation.validateNum(scan.next());
		System.out.println("");

		switch(option) {
		case 1:
			System.out.println( "You have $" + account.getBalance() + " remaining");
			break;
		case 2:
			System.out.println( "You have $" + account.getBalance() + " remaining");
			System.out.println("How much would you like to withdraw?");	
			System.out.print("Input here: ");
			int w = Validation.validateNum(scan.next());
			if(!account.withdraw(w))
			{
				System.out.println("Error Occured you broke.");
				System.out.println("Please get your stacks up.");
				System.out.println("Or check you balance before withdraw more than you have.");
				System.out.println();
				break;
			}
			
			System.out.println();
			System.out.println();
			System.out.println("New Balance is: " + account.getBalance());
			System.out.println();
			System.out.println();


			break;
		case 3:
			System.out.println( "You have $" + account.getBalance() + " remaining");;
			System.out.println("How much would you like to deposit?");	
			System.out.print("Input here: ");
			int d = Validation.validateNum(scan.next());
			if(account.deposit(d)) System.out.println("Problems has occured please try again later");
			System.out.println();
			System.out.println();
			System.out.println("New Balance is: " + account.getBalance());
			System.out.println();
			System.out.println();
			break;
		case 4:
			System.out.println("Creating new account...");
			System.out.println("How much money will you like to put in initially?");
			int amount = Validation.validateNum(scan.next());

			System.out.println();
			System.out.println();
			System.out.println("We have added a couple of more option to our application");
			System.out.println("Please read the following menu as our options may have changed:");
			System.out.println("If you want a Checking account PRESS 1");
			System.out.println("If you want a Credit account PRESS 2");
			System.out.println("If you want a Saving account PRESS 3");
			System.out.println("If you want a Loans account PRESS 4");
			System.out.println("What type of account do you want?");
			int typeof = user_type(Validation.validateNum(scan.next()));
			
			if(!account.createAccount(account,amount,typeof)) {
				System.out.println("There was a problem creating your account.");
				System.out.println("Please try again.");
				System.out.println();
				System.out.println();
			}else {
				System.out.println("Account " + account.getAccountNumber() + " has been created");
			}
			break;
		case 5:
			System.out.println("Here is the list of your accounts:");
			//print
			for(Account x: account.getAcc())
					x.toString();
			break;
		case 0: 
			System.out.println("");
			System.out.println("");
			System.out.println("");
			return false;
		}
		return true;
	}
}


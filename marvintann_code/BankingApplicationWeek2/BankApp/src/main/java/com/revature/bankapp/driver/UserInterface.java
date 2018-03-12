package com.revature.bankapp.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankapp.dao.AccountDAOImplement;
import com.revature.bankapp.dao.UserDAOImplement;
import com.revature.bankapp.pojos.Account;
import com.revature.bankapp.pojos.User;

public class UserInterface {
	
	public static boolean running = true;
	
	// below is magic for using methods from the DAO implementations without making everything static
	static UserInterface bar = new UserInterface();
	static UserDAOImplement user_dao = new UserDAOImplement(bar); // we just call foo.method() for the DAO Implementations now
	static AccountDAOImplement account_dao = new AccountDAOImplement(bar);
	
	// used to check if a string is a number or not
	public static boolean is_Int(String str) {  
		try {  
			int i = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}

	// MAIN MENU BLOCK
	public static void mainMenu() {
		System.out.println("What would you like to do?");
		System.out.println("To create an account press '1'");
		System.out.println("To login press '2'");

		Scanner input0 = new Scanner(System.in);
		int inputInt = input0.nextInt();
		
		if (inputInt == 1) {
			createUser();
		} else if (inputInt == 2) {
			login();
		} else {
			System.out.println("Please enter a valid option:");
			mainMenu();
		}
	}
	
	// LOGIN BLOCK
	public static void login() {
		System.out.println("Please enter your username:");
		Scanner input1 = new Scanner(System.in);
		String userName = input1.nextLine();
		
		if(user_dao.getUserName(userName) == null) {
			System.out.println("Username does not exist");
			System.out.println("Press '9' to create an account or any other key to try again");
			
			Scanner input2 = new Scanner(System.in);
			String ip2 = input2.nextLine();
			
			if (ip2.charAt(0) == '9') {
				createUser(); // ok
			} else {
				login();
			}
		} 
		
		boolean pass = false;
		
		do {
			System.out.println("Please enter your password");
			Scanner input2 = new Scanner(System.in);
			String pw = input1.nextLine();
			
			
			if(!user_dao.getUser(userName).getPwd().equals(pw)) {
				
				System.out.println("Password incorrect");
				System.out.println("Log in as a different user? '9'");
				System.out.println("Or try again by pressing any key");

				Scanner input3 = new Scanner(System.in);
				String ip3 = input2.nextLine();

				if (ip3.charAt(0) != '9') {
					login();
				} else {
					pass = false;
				}
			} else {
				System.out.println("Login successful!");
				pass = true;
				userOptions(userName);
			}
		} while (pass = false);
		
	}
	
	// CREATE USER BLOCK
	public static void createUser() {
		System.out.println("Please enter a unique username:");
		Scanner input1 = new Scanner(System.in);
		String userName = input1.nextLine();
		
		if (user_dao.getUserName(userName) != null) {
			System.out.println("Username already exists");
			System.out.println("Press '9' to login or any other key to enter a unque user name");
			Scanner input2 = new Scanner(System.in);
			String ip2 = input2.nextLine();
			
			if (ip2.charAt(0) != '9') {
				createUser();
			} else {
				mainMenu();
			}
		}
		
		System.out.println("Please enter a password:");
		Scanner pass1 = new Scanner(System.in);
		String pw1 = pass1.nextLine();
		System.out.println("Please re-enter the password:");
		Scanner pass2 = new Scanner(System.in);
		String pw2 = pass2.nextLine();
		
		if (pw1.equals(pw2)) {
			System.out.println("Passwords match!");
			User newUser = new User();
			newUser.setUser_id(0);
			newUser.setUser_name(userName);
			newUser.setPwd(pw1);
			
			user_dao.addUser(newUser);
			
			Account newAccount = new Account();
			newAccount.setAccount_id(0);
			newAccount.setUser_id(user_dao.getUser(userName).getUser_id());
			newAccount.setBalance(0);
			account_dao.addAccount(newAccount);
			
			System.out.println("User account created!");
			mainMenu();
		} else {
			System.out.println("Passwords do NOT match!");
			System.out.println("Try again:");
			createUser();
		}
		
	}
	
	// USER OPTIONS BLOCK
	public static void userOptions(String userName) {
		System.out.println("What would you like to do?");
		System.out.println("Select a Bank Account '1'");
		System.out.println("Create a Bank Account '2'");
		System.out.println("Logout                '3'");
		
		Scanner input = new Scanner(System.in);
		String ip1 = input.nextLine();
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		if (ip1.equals("1")) {
			System.out.println("Please choose a Bank Account: ");
			
			accounts = account_dao.getAllAcounts(user_dao.getUser(userName).getUser_id());
			for (int i = 0; i < accounts.size(); i++) {
				System.out.println("Bank Account ID "+accounts.get(i).getAccount_id()+"      '"+(i)+"'");
			}
			Scanner ac = new Scanner(System.in);
			String acc = ac.nextLine();
			
			if (is_Int(acc)) {
				
				try {
					accounts.get(Integer.parseInt(acc));
					
					System.out.println("Account Selected Successfully!");
					bankAccount(userName, accounts.get(Integer.parseInt(acc)).getAccount_id());
					
				} catch (IndexOutOfBoundsException ioobe) {
					
					System.out.println("No such Bank Account exists for this user");
					userOptions(userName);
				} 
			} else {
				System.out.println("Enter a valid choice:");
				userOptions(userName);
			}
		} else if (ip1.equals("2")) {
			createBankAccount(userName);
		} else if (ip1.equals("3")) {
			System.out.println("Successfully logged out!");
			closing();
		} else {
			System.out.println("Please choose a valid option");
			userOptions(userName);
		}
		
	}
	
	// BANK ACCOUNT OPTIONS
	public static void bankAccount (String userName, int accountId) {
		System.out.println("What would you like to do?");
		System.out.println("View Balance         '1'");
		System.out.println("Make a Deposit       '2'");
		System.out.println("Make a Withdrawal    '3'");
		System.out.println("Change Bank Accounts '4'");
		System.out.println("Logout               '5'");
		
		Scanner input = new Scanner(System.in);
		String ip1 = input.nextLine();
		
		Account curr = account_dao.getAccount(accountId);
		
		if (ip1.equals("1")) {
			System.out.println("Your balance is "+curr.getBalance());
			makeDeposit(userName, accountId);
		} else if (ip1.equals("2")) {
			makeDeposit(userName, accountId);
		} else if (ip1.equals("3")) {
			makeWithdrawal(userName, accountId);
		} else if (ip1.equals("4")) {
			userOptions(userName);
		} else if (ip1.equals("5")) {
			System.out.println("Successfully logged out!");
			closing();
		} else {
			System.out.println("Please enter a valid option:");
			bankAccount(userName, accountId);
		}
	}
	
	// CREATE BANK ACCOUNT BLOCK
	public static void createBankAccount(String userName) {
		Account newAC = new Account();
		
		User currU = user_dao.getUser(userName);
		
		newAC.setAccount_id(0); // this will be changed in the sql 
		newAC.setUser_id(currU.getUser_id()); 
		newAC.setBalance(0.0); // balance starts at zero
		
		account_dao.addAccount(newAC);
		
		System.out.println("New Bank Account Created!");
		userOptions(userName);
	}
	
	// DEPOSIT BLOCK
	public static void makeDeposit (String userName, int accountId) {
		Account curr = account_dao.getAccount(accountId);
		
		System.out.println("How much do you want to deposit?");
		Scanner depo = new Scanner(System.in);
		Double dep = 0.0;
		
		if (depo.hasNextDouble()) {
			dep = depo.nextDouble();
		} else {
			System.out.println("Please enter a valid amount:");
			makeDeposit(userName, accountId);
		}
		
		Double prevBal = curr.getBalance();
		account_dao.updateAccount(accountId, dep+prevBal);
		
		curr = account_dao.getAccount(accountId); // update Account object
		
		System.out.println("Deposit successful!");
		System.out.println("New total is "+curr.getBalance());
		
		bankAccount(userName, accountId);
	}
	
	// WITHDRAW BLOCK
	public static void makeWithdrawal (String userName, int accountId) {
		Account curr = account_dao.getAccount(accountId);
		
		System.out.println("Your current balance is "+curr.getBalance());
		System.out.println("How much do you want to withdraw?");
		Scanner amount = new Scanner(System.in);
		double wd = 0;
		
		if (amount.hasNextInt()) {
			wd = amount.nextDouble();
		} else {
			System.out.println("Please enter a correct value:");
			makeWithdrawal(userName, accountId);
		}
		
		if (wd > curr.getBalance()) {
			System.out.println("Insufficient funds, cannot complete transaction: ");
			makeWithdrawal(userName, accountId);
		} else {
			
			Double prevBal = curr.getBalance();
			account_dao.updateAccount(accountId, prevBal-wd);
			
			curr = account_dao.getAccount(accountId); // update Account object
			
			System.out.println("Withdrawal successful!");
			System.out.println("New total is "+curr.getBalance());
			
			bankAccount(userName, accountId);
		}
		
	}
	
	public static void closing() {
		running = false;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(user_dao.getUser("popipo").getPwd());
		
		
		if (running = true) {
			mainMenu();
		}
		
		
	}

}
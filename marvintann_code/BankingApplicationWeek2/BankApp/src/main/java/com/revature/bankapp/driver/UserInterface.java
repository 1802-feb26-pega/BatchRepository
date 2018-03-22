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
	public static void bankAccount(String userName, int accountId) {
		System.out.println("What would you like to do?");
		System.out.println("View Balance         '1'");
		System.out.println("Make a Deposit       '2'");
		System.out.println("Make a Withdrawal    '3'");
		System.out.println("Transfer Funds       '4'");
		System.out.println("Change Bank Accounts '5'");
		System.out.println("Logout               '6'");
		
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
			makeTransfer(userName, accountId);
	    } else if (ip1.equals("5")) {
			userOptions(userName);
		} else if (ip1.equals("6")) {
			System.out.println("Successfully logged out!");
			closing();
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
			if (dep >= 0) {
				// continue
			} else {
				System.out.println("You cannot deposit a negative amount:");
				makeDeposit(userName, accountId);
			}
		} else {
			System.out.println("Please enter a valid amount: ");
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
		
		if (amount.hasNextDouble()) {
			wd = amount.nextDouble();
			if (wd >= 0) {
				//
			} else {
				System.out.println("You cannot withdraw a negative amount:");
				makeDeposit(userName, accountId);
			}
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
	
	// TANSFER BLOCK
	public static void makeTransfer(String userName, int accountId) {
		System.out.println("Which account would you like to transfer funds to?");
		
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		accounts = account_dao.getAllAcounts(user_dao.getUser(userName).getUser_id());
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println("Bank Account ID "+accounts.get(i).getAccount_id()+"      '"+(i)+"'");
		}
		System.out.println("Go Back                '999'");
		Scanner ac = new Scanner(System.in);
		String acc = ac.nextLine();
		
		if (is_Int(acc)) {
			if (Integer.parseInt(acc) == 999) {
				bankAccount(userName, accountId);
			}
			try {
				accounts.get(Integer.parseInt(acc));
				if (accounts.get(Integer.parseInt(acc)).getAccount_id() == accountId) {
					System.out.println("Cannot transfer funds to Current Account, please Select a Different Account: ");
					makeTransfer(userName, accountId);
				}
				System.out.println("Account Selected Successfully!");
				transferAction(userName, accountId, accounts.get(Integer.parseInt(acc)).getAccount_id());
			} catch (IndexOutOfBoundsException ioobe) {
				
				System.out.println("No such Bank Account exists for this user");
				makeTransfer(userName, accountId);
			} 
		} else {
			System.out.println("Enter a valid choice:");
			makeTransfer(userName, accountId);
		}
	}
	
	public static void transferAction(String userName, int accountId1, int accountId2) {
		System.out.println("How much do you want to transfer?");
		Scanner amount = new Scanner(System.in);
		double ta = 0.0;
		Account acc1 = account_dao.getAccount(accountId1);
		Account acc2 = account_dao.getAccount(accountId2);
		
		if (amount.hasNextDouble()) {
			ta = amount.nextDouble();
			if (ta >= 0) {
				// continue
			} else {
				System.out.println("You cannot transfer a negative amount:");
				transferAction(userName, accountId1, accountId2);
			}
		} else {
			System.out.println("Please enter a correct value:");
			transferAction(userName, accountId1, accountId2);
		}
		
		if (ta > acc1.getBalance()) {
			System.out.println("Insufficient funds, cannot complete transaction: ");
			transferAction(userName, accountId1, accountId2);
		} else {
			
			Double prevBal1 = acc1.getBalance();
			account_dao.updateAccount(accountId1, prevBal1-ta);
			
			acc1 = account_dao.getAccount(accountId1); // update Account object
			
			Double prevBal2 = acc2.getBalance();
			account_dao.updateAccount(accountId2, prevBal2+ta);
			
			acc2 = account_dao.getAccount(accountId2); // update Account object
			
			System.out.println("Transfer successful!");
			System.out.println("Account with id "+acc1.getAccount_id()+" has a balance of: "+acc1.getBalance());
			System.out.println("Account with id "+acc2.getAccount_id()+" has a balance of: "+acc2.getBalance());
			
			bankAccount(userName, accountId1);
		}
		
	}
	
	public static void closing() {
		running = false;
	}
	
	public static void main(String[] args) {
		/*
		//System.out.println(user_dao.getUser("popipo").getPwd());
		Scanner pp = new Scanner(System.in);
		double jj = pp.nextDouble();
		
		System.out.println("val is "+jj);
		
		Account newAccount = new Account();
		newAccount.setAccount_id(546);
		newAccount.setUser_id(643);
		newAccount.setBalance(jj);
		
		System.out.println(newAccount.getBalance());
		
		account_dao.addAccount(newAccount);
		
		Account fetched = account_dao.getAccount(546);
		
		System.out.println("val from database is "+fetched.getBalance());*/
		
		
		if (running = true) {
			mainMenu();
		}
		
		
	}

}
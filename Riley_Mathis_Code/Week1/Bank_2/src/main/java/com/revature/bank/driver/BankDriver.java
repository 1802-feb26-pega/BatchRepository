package com.revature.bank.driver;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.bank.dao.BankDAO;
import com.revature.bank.dao.BankDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.Customer;

public class BankDriver {

	public static void main(String[] args) {
		BankDAO bankDao = new BankDAOImpl();
		
		Scanner scan = new Scanner(System.in);
		
		welcome(scan, bankDao);
		
		scan.close();
	}
		
	public static void welcome(Scanner scan, BankDAO bankDao) {
			
		String temp = "";
		System.out.println("Welcome\nRegister(r)--Login(l)");
		temp = scan.nextLine();
		if(temp.equals("r")) {
			register(scan, bankDao);
		}
		else if(temp.equals("l")) {
			login(scan, bankDao);
		}
		else {
			System.out.println("Invalid input! Must be 'r' or 'l'.");
			welcome(scan, bankDao);
		}
	}
		
	public static void register(Scanner scan, BankDAO bankDao) {
		
		String fn,ln,un,pw;
		System.out.println("Register");
		System.out.println("Firstname:");
		fn = scan.nextLine();
		System.out.println("Lastname:");
		ln = scan.nextLine();
		System.out.println("Username:");
		un = scan.nextLine();
		System.out.println("Password:");
		pw = scan.nextLine();
		
		Customer customer = new Customer(fn,ln,un,pw);
		customer = bankDao.registerCustomer(customer);
		welcome(scan,bankDao);
	}

	public static void login(Scanner scan, BankDAO bankDao ) {
		
		String un,pw,checkPw;
		System.out.println("Login");
		System.out.println("Username:");
		un = scan.nextLine();
		Customer customer = new Customer();

		customer = bankDao.getCustomerByUsername(un);
		if(customer.getUsername() == null) {
			System.out.println("That username doesn't exist!");
			login(scan, bankDao);
		}
		else {
			System.out.println("Password:");
			pw = scan.nextLine();
		
			if(pw.equals(customer.getPassword())) {
				atm(scan, bankDao, customer);
			}
			else {
				System.out.println("Incorrect password for Username: " + un);
				login(scan, bankDao);
			}
		}
	}
	public static void atm(Scanner scan, BankDAO bankDao, Customer customer) {
		
		String choice;
		System.out.println("Deposit(d) -- Withdraw(w) -- Show Balance(b) -- Transfer(t) -- New Account(n) -- Logout(l)");
		choice = scan.nextLine();
		if(choice.equals("d")) {
			deposit(scan, bankDao, customer);
		}
		else if (choice.equals("w")) {
			withdraw(scan, bankDao, customer);
		}
		else if (choice.equals("b")) {
			showBalance(scan, bankDao, customer);
		}
		else if (choice.equals("l")) {
			welcome(scan, bankDao);
		}
		else if (choice.equals("t")) {
			transfer(scan, bankDao, customer);
		}
		else if (choice.equals("n")) {
			createAccount(scan, bankDao, customer);
		}
		else {
			System.out.println("Invalid input!.");
			atm(scan, bankDao, customer);
		}
	}
	
	public static void createAccount(Scanner scan, BankDAO bankDao, Customer customer) {
		Account account = new Account(customer.getId());
		bankDao.registerAccount(account);
		System.out.println("New account created with number: " + account.getId());
		atm(scan, bankDao, customer);
	}
	
	public static void transfer(Scanner scan, BankDAO bankDao, Customer customer) {
		int currentBalance = 0;
		int amount1 = 0; 
		int amount2 = 0;
		int accNum1 = 0;
		int accNum2 = 0;
		int newBalance = 0;
		Account accFrom = new Account();
		Account accTo = new Account();
		
		System.out.println("Which account would you like pull from?");
		try {
			accNum1 = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan, bankDao, customer);
		}
		if(bankDao.getAccountById(accNum1).getCustomerId() == customer.getId()) {
			accFrom = bankDao.getAccountById(accNum1);
			System.out.println("How much?");
			try {
				amount1 = scan.nextInt();
				scan.nextLine();
			} catch(InputMismatchException ime) {
				System.out.println("Invalid input!");
				atm(scan, bankDao, customer);
			}
			
			if(amount1 > 0 && amount1 <= accFrom.getBalance()) {
				System.out.println("Which account would you like transfer into?");
				try {
					accNum2 = scan.nextInt();
					scan.nextLine();
				} catch(InputMismatchException ime) {
					System.out.println("Invalid input!");
					atm(scan, bankDao, customer);
				}
				
				if(bankDao.getAccountById(accNum2).getCustomerId() == customer.getId()) {
					accTo = bankDao.getAccountById(accNum2);
					amount2 = accTo.getBalance();
					newBalance = amount1 + amount2;
					accTo = bankDao.updateBalance(accTo, newBalance);
					showBalance(scan, bankDao, customer);
					atm(scan, bankDao, customer);
					
				} else {
					System.out.println("Invalid account number!");
					atm(scan, bankDao, customer);
				}
				
				
			} else {
				System.out.println("Input a valid amount!");
				atm(scan, bankDao, customer);
			}
		} else {
			System.out.println("Invalid account number!");
			atm(scan, bankDao, customer);
		}
		
		
			
	}
	
	public static void deposit(Scanner scan, BankDAO bankDao, Customer customer) {

		int currentBalance = 0;
		int amount = 0; 
		int accNum = 0;
		int newBalance = 0;
		Account account = new Account();
		
		System.out.println("Which account would you like to deposit into?");
		try {
			accNum = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan, bankDao, customer);
		}
		if(bankDao.getAccountById(accNum).getCustomerId() == customer.getId()) {
		
			System.out.println("How much to deposit?");
			try {
				amount = scan.nextInt();
				scan.nextLine();
			} catch(InputMismatchException ime) {
				System.out.println("Invalid input!");
				atm(scan, bankDao, customer);
			}
			
			if(amount > 0) {
				currentBalance = account.getBalance();
				newBalance = currentBalance + amount;
				account = bankDao.updateBalance(account, newBalance);
			
				showBalance(scan, bankDao, customer);
				atm(scan, bankDao, customer);
			}
			else {
				System.out.println("Input a valid amount!");
				atm(scan, bankDao, customer);
			}
		}
		else {
			System.out.println("Invalid account number!");
			atm(scan, bankDao, customer);
		}
	}
	public static void withdraw(Scanner scan, BankDAO bankDao, Customer customer) {

		int currentBalance = 0;
		int amount = 0; 
		int accNum = 0;
		int newBalance = 0;
		Account account = new Account();
		
		System.out.println("Which account would you like to withdraw from?");
		try {
			accNum = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan, bankDao, customer);
		}
		if(bankDao.getAccountById(accNum).getCustomerId() == customer.getId()) {
		
			System.out.println("How much to withdraw?");
			try {
				amount = scan.nextInt();
				scan.nextLine();
			} catch(InputMismatchException ime) {
				System.out.println("Invalid input!");
				atm(scan, bankDao, customer);
			}
			
			if(amount > 0 && amount <= account.getBalance()) {
				currentBalance = account.getBalance();
				newBalance = currentBalance - amount;
				account = bankDao.updateBalance(account, newBalance);
			
				showBalance(scan, bankDao, customer);
				atm(scan, bankDao, customer);
			}
			else {
				System.out.println("Input a valid amount!");
				atm(scan, bankDao, customer);
			}
		}
		else {
			System.out.println("Invalid account number!");
			atm(scan, bankDao, customer);
		}
	}
	
	public static void showBalance(Scanner scan, BankDAO bankDao, Customer customer) {
		
		int currentBalance = 0;
		int amount = 0; 
		int accNum = 0;
		int newBalance = 0;
		Account account = new Account();
		
		System.out.println("Which account would you like to view?");
		try {
			accNum = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan, bankDao, customer);
		}
		
		if(bankDao.getAccountById(accNum).getCustomerId() == customer.getId()) {
			account = bankDao.getAccountById(accNum);
			currentBalance = bankDao.balance(account);
			System.out.println("Current balance for that account is: " + currentBalance);
			atm(scan, bankDao, customer);
			
		} else {
			System.out.println("Invalid account number!");
			atm(scan, bankDao, customer);
		}
	}
//		List<Customer> customers = bankDao.getAllCustomers();
//		
//		for(Customer a : customers) {
//			System.out.println(a);
//		}
//		
//		
//		Customer customer = new Customer();
//		customer = bankDao.getCustomerById(1);
//		System.out.println(customer);
//		
//		Account account = new Account();
//		account = bankDao.getAccountById(1);
//		System.out.println(account);
//		
//		Customer temp = new Customer("Edgar","Martinez","edog","mariners");
//		
//		customer = bankDao.registerCustomer(temp);
//		System.out.println(customer);
//		
//		Account tempA = new Account(1);
//		account = bankDao.registerAccount(tempA);
//		System.out.println(account);
//		
//		System.out.println(bankDao.balance(account));

}

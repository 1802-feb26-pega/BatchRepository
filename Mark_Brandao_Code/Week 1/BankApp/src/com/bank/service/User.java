package com.bank.service;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private double balance = 0;
	
	public User(String username, String password, String firstname, String lastname, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.balance = balance;
	}
	
	public User(String[] args) {
		this(args[0], args[1], args[2], args[3], Double.parseDouble(args[4]));
	}
	
	public  User() {
		super();
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		if (balance < 0) {
			System.out.println("\nYou cannot have a negative balance!");
		} else {
			this.balance = balance;
		}
	}
	
	public void adjustBalance(double adjustment) {
		if(this.balance + adjustment < 0) {
			System.out.println("You can't have a negative balance!");
		} else {
			this.balance += adjustment;
		}
	}
	
	@Override
	public String toString() {
		return this.username + "," + this.password + "," + this.firstname + "," + this.lastname + "," + this.balance + "\n";
	}
	
	public void login() {
		System.out.println("Enter your username.");
		String un = Application.scan.nextLine();
		boolean uncheck = this.verifyUsername(un);
		if (!uncheck) {
			System.out.println("That username doesn't exist yet.");
		}
		System.out.println("Enter your password.");
		String pw = Application.scan.nextLine();
		User pwcheck = this.verifyPassword(un, pw);
		if (pwcheck == null) {
			System.out.println("Incorrect password.");
		} else {
			Application app = new Application();
			app.mainMenu(pwcheck);
		}
		
	}
	
	public void makeAccount() {
		boolean invalidname = true;
		DataAccess da = new DataAccess();
		do {
			System.out.println("Enter a user name: ");
			String inputname = Application.scan.nextLine();
			
			if(!da.isUser(inputname)) {
				invalidname = false;
				this.setUsername(inputname);
			} else {
				System.out.println("\nThat username is unavailable. Please select another username.");
			}
		} while (invalidname);
		System.out.println("Enter a password: ");
		this.setPassword(Application.scan.nextLine());
		System.out.println("Enter your first name: ");
		this.setFirstname(Application.scan.nextLine());
		System.out.println("Enter your last name: ");
		this.setLastname(Application.scan.nextLine());
		this.setBalance(0);
		
		DataAccess db = new DataAccess();
		ArrayList<User> users = db.readUsers();
		users.add(this);
		db.writeUsers(users);
	}

	
	public User verifyPassword(String username, String password) {
		DataAccess db = new DataAccess();
		ArrayList<User> users = db.readUsers();
		for(User user : users) {
			if(user.username.equals(username) && user.password.equals(password)) {
				return user;
			}
		}
		
		return null;
	}
	
	public boolean verifyUsername(String un) {
		DataAccess db = new DataAccess();
		ArrayList<User> users = db.readUsers();
		for(User user : users) {
			if(user.username.equals(un)) {
				return true;
			}
		}
		
		return false;
	}

	public void updateData() {
		DataAccess db = new DataAccess();
		db.updateBalance(this);
	}
}
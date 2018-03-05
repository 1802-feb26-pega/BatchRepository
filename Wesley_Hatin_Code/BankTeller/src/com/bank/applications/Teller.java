package com.bank.applications;

public class Teller {
	
	private String username;
	private String firstName;
	private String lastName;
	private String password="";
	private double balance;
	private BankDatabase db=new BankDatabase();
	
	// create an account with a unique email or username
	public boolean createAccount(String user, String first, String last, String pass) {
		if(!db.usernameValidation(user)) {
			db.writeNewClient(user, first, last, pass);
			return true;
		}
		return false;
	}
	
	// log in 
	public boolean logIn(String username, String password) {
		if(db.usernameValidation(username)) {
			String[] entry = db.retrieveClientEntry(username);
			
			if(String.valueOf(password).equals(String.valueOf(entry[3]))) {
				this.username=username;
				this.password=password;
				firstName=entry[1];
				lastName=entry[2];
				balance=Double.valueOf(entry[4]);
				return true;
			}
		}
		return false;
	}
	
	//log out
	public void logOut() {
		this.username=null;
		this.password=null;
	}
	
	//deposit money
	public void deposit(int amount) {
	}
	
	//withdraw money
	public boolean withdraw(int amount) {
		return false;
	}
	
	//view balance
	public double getBalance() {
		return balance;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return (firstName+" "+lastName);
	}

}

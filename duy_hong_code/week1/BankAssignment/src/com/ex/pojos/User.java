package com.ex.pojos;

public class User {
	
	//public static HashMap<String, User> = new HashMap<String, User>();
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private double balance;
	
	public User() {}
	
	public User(String first, String last, String name, String pass, double bal) {
		super();
		this.firstName = first;
		this.lastName = last;
		this.userName = name;
		this.password = pass;
		this.balance = bal;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString( ) {
		return userName + ":" + firstName + ":" + lastName + ":" + password + ":" + balance;
	}
}

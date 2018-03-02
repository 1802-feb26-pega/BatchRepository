package com.project.Logic;

import java.io.Serializable;

public class User implements Serializable {
	private static final String serializeFile = "bin/data/lastUser.txt";
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String emailAddress;
	private String username;
	private String password;
	private double balance;
	
	/**
	 * Constructs a new user.
	 * @param firstName
	 * @param middleInitial
	 * @param lastName
	 * @param emailAddress
	 * @param username
	 * @param password
	 */
	public User(String firstName, String middleInitial, String lastName, String emailAddress, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Constructs a new user with a specific balance.
	 * @param firstName
	 * @param middleInitial
	 * @param lastName
	 * @param emailAddress
	 * @param username
	 * @param password
	 * @param balance
	 */
	public User(String firstName, String middleInitial, String lastName, String emailAddress, String username,
			String password, double balance) {
		this(firstName, middleInitial,lastName,emailAddress,username,password);
		this.balance = balance;
	}

	/**
	 * Constructs an empty user.
	 */
	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double addBalance(double toAdd) {
		this.balance += toAdd;
		return this.balance;
	}
	
	public double subtractBalance(double toSubtract) {
		this.balance -= toSubtract;
		return this.balance;
	}

	public String getWholeName() {
		return firstName + " " + middleInitial + " " + lastName;
	}
	
	@Override
	public String toString() {
		return firstName + "::" + middleInitial + "::" + lastName
				+ "::" + emailAddress + "::" + username + "::" + password + "::" + balance + "\n";
	}
	
	/**
	 * Instantiates a new user using a string array. The arguments in the array are:
	 * 
	 * 1 firstName
	 * 2 middleInitial
	 * 3 lastName
	 * 4 emailAddress
	 * 5 username
	 * 6 password
	 * 7 balance (Must be able to be parsed to double.)
	 * 
	 * @param args - The list of arguments
	 * @return
	 */
	public static User instantiateUser(String[] args) {
		if (args.length != 7) return null;
		return new User(args[0],args[1],args[2],args[3],args[4],args[5],Double.parseDouble(args[6]));
	}
	
	
	
	

}

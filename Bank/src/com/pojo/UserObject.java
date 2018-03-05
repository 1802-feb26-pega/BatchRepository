package com.pojo;

public class UserObject {
	
	private String username;
	private String password;
	
	private int balance;
	
	public UserObject() {}
	
	public UserObject(String username, String password) {
		this.username = username;
		this.password = password;
		
		this.balance = 0;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return username + " " + password + " " + balance + "\n";
	}

}

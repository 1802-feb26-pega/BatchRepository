package com.bank.objects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

import com.bank.io.DataIO;

public class Bank{

	private ArrayList<User> users;
	private boolean loggedIn;
	private User user;
	private DataIO io;
	
	public Bank() {
		this.users = new ArrayList<>();
		io = new DataIO();
	}
	
	public boolean startBank() {
		this.users = io.readUsers();
		return true;	
	}
	
	public void closeBank() {
		io.clearFile();
		for(User u : this.users) {
			io.writeUser(u);
		}
		return;
	}

	public boolean login(String email, String password) {
		for(User u : this.users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					this.user = u;
					this.loggedIn = true;
					return true;
				}
			} 	
		}
		return false;
	}
	
	public boolean logout() {
		this.loggedIn = false;
		this.user = null;
		return true;
	}
	
	public boolean register(String firstName, String lastName, String email, String password) {
		for(User u : this.users) {	
			if(u.getEmail().equals(email)) {				
				return false;
			}
		}	
		
		User newUser = new User(firstName, lastName, email, password, 0.0);	
		this.users.add(newUser);
		
		
		/*for(User u : users) {
			io.writeUser(u);
		}*/
		
		return true;		
	}
	
	public boolean withdraw(double amount) {
		if(!this.isLoggedIn()) {
			System.out.println("Not logged in.");
			return false;
		}
		
		double balance = user.getBalance();
		
		if(balance < amount) {
			return false;
		} 
		
		this.user.setBalance(balance - amount);
		
		/*for(User u : this.users) {
			io.writeUser(u);
		}*/
		return true;
	}
	
	public boolean deposit(double amount) {
		
		if(!this.isLoggedIn()) {
			System.out.println("Not logged in.");
			return false;
		}
		
		user.setBalance(user.getBalance() + amount);
		
		/*for(User u : users) {
			io.writeUser(u);
		}*/
		
		return true;
	}
	
	public String getBalance() {
		if(!this.isLoggedIn()) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US)); //does not implement auto-closable
		
		try {
			output = nf.valueToString(user.getBalance());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	

	public ArrayList<User> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<User> list) {
		this.users = list;
	}

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(boolean bool) {
		this.loggedIn = bool;
	}

	public User getCurrentUser() {
		return this.user;
	}

	public void setCurrentUser(User currentUser) {
		user = currentUser;
	}
}

package com.assign.pojos;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.text.NumberFormatter;
import com.assign.io.DataFileIO;

public class Bank{

	private ArrayList<User> users;
	private boolean loggedIn;
	private User currentUser;
	private DataFileIO io;
	
	public Bank() {
		users = new ArrayList<>();
		io = new DataFileIO();
	}
	
	public boolean openBank() {
		this.users = io.readUsers();
		return true;
		
	}
	
	public boolean closeBank() {
		
		io.clearFile();
		
		for(User u : users) {
			io.writeUsers(u);
		}
		return false;
	}

	public boolean login(String email, String password) {
		
		for(User u : users) {
			
			if(u.getEmail().equals(email)) {
				
				if(u.getPassword().equals(password)) {
					currentUser = u;
					loggedIn = true;
					return true;
				}
				else {
					return false;
				}	
			}
		}
		return false;
	}
	
	public boolean logout() {
		loggedIn = false;
		currentUser = null;
		return true;
	}
	
	public boolean register(String firstName, String lastName, String email, String password) {
		
		for(User u : users) {
			
			if(u.getEmail().equals(email)) {				
				return false;
			}
		}	
		User newUser = new User(firstName, lastName, email, password, 0.0);	
		users.add(newUser);
		return true;		
	}
	
	public boolean withdraw(double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		
		double balance = currentUser.getBalance();
		
		if(balance < amount) {
			
			return false;
		}
		else {
			currentUser.setBalance(balance - amount);
			return true;
		}	
	}
	
	public boolean deposit(double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		currentUser.setBalance(currentUser.getBalance() + amount);
		return true;
	}
	
	public String viewBalance() {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(currentUser.getBalance());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public boolean getIsLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	
	
	
	
	
}

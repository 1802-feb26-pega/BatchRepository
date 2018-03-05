package com.revature.bank1;

import java.util.HashMap;

public class Bank {

	private HashMap<String, User> currentData;
	private User currentUser;
	private DataSerializer ds;
	
	public Bank() {
		
		ds = DataSerializer.getInstance();
		currentData = ds.deSerialize();	
	}//constructor
	
	public Bank(User user) {
		this();
		currentUser = user;
	}//constructor
	
	public void deserializeUserData() {
		
		currentData = ds.deSerialize();
	}//deserializeUserData()
	
	
	/*
	 * a valid user
	 */
	public boolean userNameInUse(String userName) {
		
		if(currentData.containsKey(userName)) {
			
			return true;
		}//if
		return false;
	}//userNameInUse()
	
	/*
	 * takes in a username and password and compares them to
	 * the map of users.  If there is no match, returns false.
	 * If there is a match, currentUser field is set and true
	 * is returned.
	 */
	public boolean login(String userName, String password) {
		
		User user = null;
		if(userNameInUse(userName)) {
			
			user = currentData.get(userName);
			if(user.getPassword().equals(password)) {
				
				currentUser = user;
				return true;
			}//if
		}//if
		return false;
	}//login()
		
		
	
	public void newUser(String userName, String password) {
		
		currentData.put(userName, new User(userName, password, 0.0f));
	}//newUser(2)
	
	
	public void newUser(String userName, String password, float startingBalance) {
		
		currentData.put(userName, new User(userName, password, startingBalance));
	}//newUser(3)
	
	
	public void removeUser(String userName) {
		
		currentData.remove(userName);
	}//removeUser()
	
	public void changePassword(String newPassword) {
		
		currentUser.setPassword(newPassword);
	}//changePassword()
	
	public float getBalence() {
		
		return currentUser.getBalance();
	}//getBalence()
	
	public void deposit(float deposit) {
		
		currentUser.setBalance(currentUser.getBalance() + deposit);
	}//deposit()
	
	public float withdraw(float withdraw) {
		
		if(withdraw <= currentUser.getBalance()) {
			
			currentUser.setBalance(currentUser.getBalance() - withdraw);
			return withdraw;
		}//if
		else {
			
			return -1.0f;
		}//else
	}//withdraw
	
	public void logout() {
		
		currentUser = null;
	}//logout
	
	public void exit() {
		
		ds.serialize(currentData);
		currentData = null;
		currentUser = null;
	}
        
		
	public void dataDump() {
		
		if(currentData.isEmpty()){
			
			System.out.println("---------no users---------");
		}//if
		else {
			
			for (String userName: currentData.keySet()){
	
	            User user = currentData.get(userName);  
	            System.out.println(user.toString());  
			}//for	
		}//else
	}//dataDump()	
		
		
	public void clearData() {
		
		currentData.clear();
	}
	
	/*
	 * Deprecated code used to test serialization.
	 * this code should never be used.  It was left
	 * hear as an example for the programmer
	 */
	private void testSerialization() {
		
		HashMap<String, User> users = new HashMap<String, User>();
		users.put("user1", new User("user1", "password1", 100.0f));
		users.put("user2", new User("user2", "password2", 200.0f));
		users.put("user3", new User("user3", "password3", 300.0f));
		users.put("user4", new User("user4", "password4", 400.0f));
		ds.serialize(users);
	}//testSerialization()
	

	
	
}//Bank

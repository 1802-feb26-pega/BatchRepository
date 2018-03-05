package com.revature.bank1;

import java.util.HashMap;


/*
 * bank keeps track of Users.  It allows for the creation
 * deletion, and manipulation of User objects, as well as
 * the ability to save data post program
 */
public class Bank {

	private HashMap<String, User> currentData;
	private User currentUser;
	private DataSerializer ds;
	
	
	/*
	 * constructor automatically deserializes data
	 */
	public Bank() {
		
		ds = DataSerializer.getInstance();
		currentData = ds.deSerialize();	
	}//constructor

	
	
	/*
	 * returns true if currentData contains a key mapping for input
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
	
	
	
	/*
	 * input Strings are used to create a new User Instance and add
	 * it to currentData
	 */
	public void newUser(String userName, String password, float startingBalance) {
		
		currentData.put(userName, new User(userName, password, startingBalance));
	}//newUser()
	
	
	
	/*
	 * remove a user from currentData 
	 * @Param userName : the username of the user being removed
	 */
	public void removeUser(String userName) {
		
		currentData.remove(userName);
	}//removeUser()
	
	
	/*
	 * change the password of the User stored in currentUser
	 */
	public void changePassword(String newPassword) {
		
		currentUser.setPassword(newPassword);
	}//changePassword()
	
	
	
	/*
	 * return the balance of the User stored in currentUser as a float
	 */
	public float getBalence() {
		
		return currentUser.getBalance();
	}//getBalence()
	
	
	/*
	 * add to the balance of the User stored in currentUser
	 */
	public void deposit(float deposit) {
		
		currentUser.setBalance(currentUser.getBalance() + deposit);
	}//deposit()
	
	
	
	/*
	 * withdraw from the User stored in currentUser's account.
	 * if funds are insufficient, -1.0f is returned, else the
	 * withdrawn amount is returned as a float
	 */
	public float withdraw(float withdraw) {
		
		if(withdraw <= currentUser.getBalance()) {
			
			currentUser.setBalance(currentUser.getBalance() - withdraw);
			return withdraw;
		}//if
		else {
			
			return -1.0f;
		}//else
	}//withdraw
	
	
	/*
	 * sets currentUser to null
	 */
	public void logout() {
		
		currentUser = null;
	}//logout
	
	
	/*
	 * serializes the the HashMap currentData, then clears
	 * currentData and currentUser fields
	 */
	public void exit() {
		
		ds.serialize(currentData);
		currentData = null;
		currentUser = null;
	}//exit()
        
		
	/*
	 * prints the user information of all users stored in currentData
	 */
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
		
		
	
	/*
	 * clears all entries in currentData.
	 * does NOT set field to null
	 */
	public void clearData() {
		
		currentData.clear();
	}//clearData()
	
	
	
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

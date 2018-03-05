package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.pojo.UserObject;

public class UserIO {

	private static Set<UserObject> users = new HashSet<>();
	
	static final String filename = "src/data/data.txt";
	
	public static void writeFile() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
			for (UserObject user: users) {
				bw.write(user.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readFile() {
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while((line = br.readLine()) != null) {
				String[] data = line.split(" ");
				UserObject temp = new UserObject();
				temp.setUsername(data[0]);
				temp.setPassword(data[1]);
				temp.setBalance(Integer.parseInt(data[2]));
				users.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Updates the balance of the user after withdraws and deposits
	public static void update(UserObject user) {
		for (UserObject aUser: users) {
			if (user.getUsername().equals(aUser.getUsername())) {
				aUser.setBalance(user.getBalance());
			}
		}
	}
	
	// Insert a new user into the file. If that user is already a user, do not insert
	public void insert(UserObject user) {
		users.add(user);
	}
	
	// Fetches user data from file when user logs in
	public UserObject retrieve(UserObject user) {
		//System.out.println("user: " + user.getUsername());
		for (UserObject userO: users) {
			//System.out.println("userO: " + userO.getUsername());
			if (user.getUsername().equals(userO.getUsername()))
				return userO;
		}
		return null;
	}
	
	/*
	 *  Search hashmap to verify if username exists. Return true if username exists and 
	 *  false is username does not exist.
	 */
	public boolean verifyUser(UserObject user) {
		for (UserObject userO: users) {
			if (userO.getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}
}

package com.bank.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataAccess {
	final static String FILENAME = "src/data/clients.txt";
	
	public void writeUsers(ArrayList<User> users) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, false))){
			String filecontent = "";
			for(User user : users) {
				filecontent += user.toString();
			}
			bw.write(filecontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<User> readUsers(){
		ArrayList<User> users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(FILENAME))){
			String content = null;
			while ((content = br.readLine()) != null) {
				String[] data = content.split(",");
				User temp = new User(data);
				users.add(temp);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public boolean isUser(String input) {
		ArrayList<User> users = readUsers();
		for (User user : users) {
			if(user.getUsername().equals(input)) {
				return true;
			}
		}
		return false;
	}
	
	public void updateBalance(User user_to_update) {
		ArrayList<User> users = readUsers();
		for (User user: users) {
			if(user.getUsername().equals(user_to_update.getUsername())) {
				user.setBalance(user_to_update.getBalance());
			}
		}
		writeUsers(users);
	}
}

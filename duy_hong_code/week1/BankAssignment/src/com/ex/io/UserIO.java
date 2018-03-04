package com.ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.ex.pojos.User;

public class UserIO {
	final static String filename = "src/data/users.txt";
	
	public void writeUser(User user) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
			bw.write(user.toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, User> readUsers(){
		//List<User> students = new ArrayList<User>();
		HashMap<String, User> users = new HashMap<String, User>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = null;
			
			while((line=br.readLine()) != null) {
				
				String[] data = line.split(":");
				User temp = new User();
				
				temp.setUserName(data[0]);
				temp.setFirstName(data[1]);
				temp.setLastName(data[2]);
				temp.setPassword(data[3]);
				temp.setBalance(Double.parseDouble(data[4]));
				
				users.put(data[0], temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}

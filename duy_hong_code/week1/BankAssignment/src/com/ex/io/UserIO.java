package com.ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
			String[] data;	// = line.split(":");
			
			while((line=br.readLine()) != null) {
				
				data = line.split(":");
				//System.out.println(Arrays.toString(data));
				if(data.length == 5) {
					/*System.out.println(data[0]);
					System.out.println(data[1]);
					System.out.println(data[2]);
					System.out.println(data[3]);
					System.out.println(data[4]);*/
					
					User temp = new User();
					
					temp.setUserName(data[0]);
					temp.setFirstName(data[1]);
					temp.setLastName(data[2]);
					temp.setPassword(data[3]);
					temp.setBalance(Double.parseDouble(data[4]));
					
					users.put(data[0], temp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}

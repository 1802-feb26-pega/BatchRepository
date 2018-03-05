package com.assign.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.assign.pojos.User;

public class DataFileIO {

	final static String fileName = "src/com/assign/data/Users.txt";
	
	public void clearFile() {
		try(PrintWriter pw = new PrintWriter(fileName)){
			//This clears the file to avoid duplicate data when writing back to the file at bank close.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeUsers(User user) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){
			
			bw.write(user.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> readUsers(){
		
		ArrayList<User> users = new ArrayList<User>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			
			String line = null;
			while((line = br.readLine()) != null && line != "") {
				String[] data = line.split(":");
				User temp = new User(data[0],data[1],data[2], data[3], Double.parseDouble(data[4]));
				users.add(temp);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
		return users;
		
	}
	
}

package com.bank.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.bank.objects.User;

public class DataIO {
final static String fileName = "src/com/bank/data/Data.txt";
	
	public void writeUser(User user) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))){ //try with resources, don't need to close in finally
			bw.write(user.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void clearFile() {
		try (PrintWriter pw = new PrintWriter(fileName)){
			//do nothing, printwriter clears the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<User> readUsers(){
		ArrayList<User> users = new ArrayList<User>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) { //again, don't have to close with finally
			String line = null;
			while((line = br.readLine()) != null && line != "") {
				String[] data = line.split(" - ");
				User user = new User(data[0], data[1], data[2], data[3], Float.parseFloat(data[4]));
				users.add(user);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
				
		return users;	
	}
}

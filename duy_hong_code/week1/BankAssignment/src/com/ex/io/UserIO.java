package com.ex.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> readUsers(){
		List<User> students = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = null;
			while((line=br.readLine()) != null) {
				String[] data = line.split(":");
				User temp = new User();
				temp.setName(data[0]);
				temp.setAge(Integer.parseInt(data[1]));
				students.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}
}

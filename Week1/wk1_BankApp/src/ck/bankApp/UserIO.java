package ck.bankApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ck.bankApp.User;

public class UserIO {
	final static String filename = "./accounts.txt";
	
	public void writeUser(User aUser)
	{
		//try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)))
		try(FileWriter bw = new FileWriter(filename,false))
		{
			bw.write(aUser.toString()+"\n");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}//write user
	
	public List<User> readUsers(){
		List<User> users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line = null;
			while((line=br.readLine()) != null)
			{
				String[] data = line.split(":");
				User temp = new User();
				//parse the data fields and add them to a User instance, add that to the list of Users
				temp.setUsername(data[0]);
				temp.setFirstName(data[1]);
				temp.setLastName(data[2]);
				temp.setBalance(Double.parseDouble(data[3]));
				
				users.add(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}//read users
}

package ck.bankApp;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static UserIO io = new UserIO();
	
	public static void main(String[] args)
	{
		//UserIO io = new UserIO();
		
		List<User> users = io.readAllUsers();// keep this

		/*
		for(User u : users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}	
		
		System.out.println("====================\n====================");
		User bb = new User("j","K","L",4.0);
		users.add(bb);
		
		for(User u: users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}
		
		io.writeAllUsers(users);
		System.out.println("\n\nwritten to file");
		*/
		
		ClientUI cui = new ClientUI();
		int topSelection = cui.topUI();
		
		switch(topSelection)
		{
		case 1:
			users = cui.createNewUser(users);
			System.out.println("User created successfully");
			break;
		case 2:
			//login for existing user
			break;
		case 3:
			//exit - print message/do nothing
			break;
		default:
			System.out.println("Something went wrong");
		}//switch
		
		System.out.println("Writing accounts.txt");
		io.writeAllUsers(users);
	}//main method
}

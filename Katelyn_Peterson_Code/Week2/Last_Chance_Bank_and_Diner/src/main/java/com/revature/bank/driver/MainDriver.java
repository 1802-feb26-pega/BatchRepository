package com.revature.bank.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.User;

public class MainDriver
{
	// DAO Variables
	static private UserDAOImpl userDao = new UserDAOImpl();
	
	// Menu Driver
	//private MenuDriver menu;
	
	// Validation Driver
	

	public static void main(String[] args)
	{
		// 1. Variables
		Scanner userInput = new Scanner(System.in);
		List<User> transferUsers;
		HashMap<String, User> users = new HashMap<>();
		
		// Menu Variable
		int option = 0;
		
		// 2. Input
		transferUsers = userDao.getAllUsers();
		for(User x : transferUsers)
		{
			users.put(x.getUserName(), x);
		}
		
		// 3. Calculation
		// Opening Menu - Welcome and option between register, log-in, and close.
		
		System.out.println("	Welcome to Last Chance Bank and Diner.");
		System.out.println("		Menu or Special Order?");
        System.out.println();
        System.out.println("	     _____________ ______________ ");
        System.out.println("	    (_,---------.(`______________`) ");
        System.out.println("	                 \\               / ");
        System.out.println("	                  `-------------' ");
        System.out.println();
        System.out.println("Log in or Register?");
        
        do
        {
	        System.out.println("To Log In, Type '1'");
	        System.out.println("To Register, Type '2'");
	        System.out.println("To Close the Program, Type '3'");
	        
	        ValidationDriver.validateInput(userInput, "Choice");
	        option = userInput.nextInt();
	        userInput.nextLine();
	        
	        ValidationDriver.choiceVal(option, 1, 3);
	        
	        switch(option)
	        {
	        case 1:
	        {
	        	// Log In
	        	MenuDriver.logIn(users);
	        	break;
	        }
	        case 2: 
	        {
	        	// Register
	        	MenuDriver.register(users);
	        	break;
	        }
	        }
        	
        }while(option != 3);
		
	}
}
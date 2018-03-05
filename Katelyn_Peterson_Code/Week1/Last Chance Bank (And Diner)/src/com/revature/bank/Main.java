package com.revature.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

//Name: Katelyn Peterson
//Date: Mar. 3rd, 2018

public class Main
{

	public static void main(String[] args)
	{
		// Main will handle the menus and user input validation.
		// For now, it will also handle the file interaction and loading users into memory.
		
		// 1. Variables
		Scanner userInput = new Scanner(System.in);
		HashMap<String, User> users = new HashMap<>();
		File userData = new File("src/com/revature/bank/data.txt");
		
		// Menu variable
		int option = 0;
		
		// User Variables
		String userName;
		String firstName;
		String lastName;
		String password;
		Double amount;
		DecimalFormat newFormat = new DecimalFormat("#.00");
		newFormat.setRoundingMode(RoundingMode.DOWN);
		Boolean valid = false;
		
		// 2. Input
		if(userData.exists())
		{
			// Load File
			loadUsers(users, userData);
		}
		
		// 3. Calculation
		
		// Opening Menu - Welcome and option between register, log-in, and close.  Close will save users to 
		// the txt file
		
		System.out.println("	Welcome to Last Chance Bank and Diner.");
		System.out.println("		Menu or special order?");
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
	        
	        validateInput(userInput, "Choice");
	        option = userInput.nextInt();
	        userInput.nextLine();
	        
	        while(option < 1 | option > 3)
	        {
	        	System.out.println("Very Funny.");
	        	System.out.println("No, really, what do you want to do?");
		        validateInput(userInput, "Choice");
		        option = userInput.nextInt();
		        userInput.nextLine();
	        }
	        
	        switch(option)
	        {
	        case 1:
	        {
				// Log In - asks for username and password.  Checks username against keys first, then, if username 
				// does not match, says username not found and, for now, returns to Welcome
				// If username found, checks password against stored password.  For now, if password is incorrect,
				// returns to Welcome
				// If I have time, I will expand on password first, allowing multiple tries, then username,
				// offering the option to either retry or register
				do
				{
					valid = false;
		        	System.out.println("Username? ");
		        	userName = userInput.nextLine();
		        	
		        	// Validation
		        	if(!users.isEmpty())
		        	{
			        	for(String key : users.keySet())
			        	{
			        		//System.out.println("Key: " + key);
			        		//System.out.println("userName: " + userName);
			        		if (key.equals(userName))
			        		{
			        			valid = true;
			        			break;
			        		}
			        	}
			        	
			        	if(!valid)
			        	{
			        		System.out.println("That Username does not match our files.");
			        		System.out.println("Type 1 to try again: ");
			    	        validateInput(userInput, "Choice");
			    	        option = userInput.nextInt();
			    	        userInput.nextLine();
			    	        
			    	        if (option != 1)
			    	        {
			    	        	break;
			    	        }
			        	}
		        	}
		        	else
		        	{
		        		System.out.println("We do not have any users");
		        		break;
		        	}
					
				}while (!valid);
				
				//System.out.println("Here");
				
				// Password
				do
				{
					valid = false;
		        	System.out.println("Password? ");
		        	password = userInput.nextLine();
		        	
		        	// Validation
		        	
		        	
		        	if(!users.get(userName).getPassword().equals(password))
		        	{
		        		System.out.println("That Password does not match our files.");
		        		System.out.println("Type 1 to try again: ");
		    	        validateInput(userInput, "Choice");
		    	        option = userInput.nextInt();
		    	        userInput.nextLine();
		    	        
		    	        if (option != 1)
		    	        {
		    	        	break;
		    	        }
		        	}
		        	else
		        	{
		        		valid = true;
		        	}
					
				}while (!valid);
				
				// Account - shows username and account balance with three options: deposit, withdraw, and log out.
				// Log out returns to Welcome Screen
				for(int x = 0; x < 10; x++)
				{
					System.out.println();
				}
				
				do
				{
					System.out.println("Welcome: " + users.get(userName).getFirstName()
							+ " " + users.get(userName).getLastName());
					System.out.println();
					System.out.println("Balance: " + users.get(userName).getAccount().getBalance());
					System.out.println();
					System.out.println("Would you like to deposit or withdraw?");
					System.out.println("Type 1 to deposit and 2 to withdraw.");
					System.out.println("Type 3 to exit back to Welcome Screen");
	    	        validateInput(userInput, "Choice");
	    	        option = userInput.nextInt();
	    	        userInput.nextLine();
	    	        
	    	        while(option < 1 | option > 3)
	    	        {
	    	        	System.out.println("Very Funny.");
	    	        	System.out.println("No, really, what do you want to do?");
	    		        validateInput(userInput, "Choice");
	    		        option = userInput.nextInt();
	    		        userInput.nextLine();
	    	        }
					
					
					// Deposit and withdraw - asks for the amount to be added/withdrawn.  If exception thrown, 
					// displays an error message above Account
	    	        if(option == 1)
	    	        {
	    	        	System.out.println("Enter amount to deposit");
	    	        	
	    	        	validateInput("Amount", userInput);
	    		        amount = userInput.nextDouble();
	    		        userInput.nextLine();
	    		        
	    		        amount = Double.valueOf(newFormat.format(amount));
	    		        
	    		        users.get(userName).getAccount().credit(amount);
	    	        	
	    	        }
	    	        else if (option == 2)
	    	        {
	    	        	System.out.println("Enter amount to withdraw");
	    	        	
	    		        validateInput("Amount", userInput);
	    		        amount = userInput.nextDouble();
	    		        userInput.nextLine();
	    		        
	    		        amount = Double.valueOf(newFormat.format(amount));
	    		        
	    		        try
	    		        {
	    		        	users.get(userName).getAccount().debit(amount);
	    		        }
	    		        catch (IllegalArgumentException ex)
	    		        {
	    		        	System.out.println("Withdrawal Amount Exceeds Account Balance!");
	    		        	System.out.println("Nice try...better luck next time.");
	    		        }
	    	        }
	    	        else
	    	        {
	    	        	option = 9;
	    	        }
					
				}while(option != 9);
	        	
	        	break;
	        }
	        case 2:
	        {
				do
				{
		        	// Register - asks for desired username first.  Checks username against keys and asks for a new 
					// username if key is present.  Once username is set, asks for password, first name, and last name
					// and adds to HashMap
					valid = false;
		        	System.out.println("Username? ");
		        	userName = userInput.nextLine();
		        	
		        	// Validation
		        	if(!users.isEmpty())
		        	{
			        	for(String key : users.keySet())
			        	{
			        		//System.out.println("Key: " + key);
			        		//System.out.println("userName: " + userName);
			        		if (key.equals(userName))
			        		{
			        			valid = true;
			        			break;
			        		}
			        	}
			        	
			        	if(valid)
			        	{
			        		System.out.println("That Username is already taken.");
			        	}
		        	}
					
				}while (valid);
				
				// Other user data
	        	System.out.println("Password? ");
	        	password = userInput.nextLine();
	        	System.out.println("First Name? ");
	        	firstName = userInput.nextLine();
	        	System.out.println("Last Name? ");
	        	lastName = userInput.nextLine();
	        	
	        	users.put(userName, new User(userName, password, firstName, lastName, new Account()));
	        	
	        	System.out.println("Congratulations, you are now registered.  Please log in with your "
	        			+ "Username and Password.");
	        	System.out.println();
	        	
	        	break;
	        }
	        default:
	        {
	        	break;
	        }
	        }
			
		}while (option != 3);
		
		// 4. Output
		// Before closing, save users
		saveUsers(users);

	}
	
	// Function for loading file
	public static void loadUsers(HashMap<String, User> users, File userData)
	{
		try
		{
			// Variables
			Scanner input = new Scanner(userData);
			String userName = "";
			String password = "";
			String firstName = "";
			String lastName = "";
			Double balance = 0.00;
			
			while(input.hasNext())
			{
				// Get Input
				String line = input.nextLine();
				String [] strArr = line.split(",");
				
				// Set current user variables
				userName = strArr[0];
				password = strArr[1];
				firstName = strArr[2];
				lastName = strArr[3];
				balance = Double.parseDouble(strArr[4]);
				
				// Add user to map
				users.put(userName, new User(userName, password, firstName, lastName, new Account(balance)));
			}
			
			input.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Function for saving file
	public static void saveUsers(HashMap<String, User> users)
	{
		try
		{
			PrintWriter input = new PrintWriter("src/com/revature/bank/data.txt");
			
			for (String key : users.keySet())
			{
				input.print(users.get(key).getUserName() + ",");
				input.print(users.get(key).getPassword() + ",");
				input.print(users.get(key).getFirstName() + ",");
				input.print(users.get(key).getLastName() + ",");
				input.println(users.get(key).getAccount().getBalance());
			}
			
			input.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// Number validation for Integers
	static void validateInput(Scanner input, String num)
	{
		System.out.println("Enter " + num + ": ");
		
		while(!input.hasNextInt())
		{
			//if (!input.hasNextInt())
			//{
				System.out.println("Please enter a number.");
				System.out.println("Enter " + num + ": ");
				input.nextLine();
			//}			
		}
	}
	
	static void validateInput(String num, Scanner input)
	{
		System.out.println("Enter " + num + ": ");
		
		while(!input.hasNextDouble())
		{
			//if (!input.hasNextInt())
			//{
				System.out.println("Please enter a number.");
				System.out.println("Enter " + num + ": ");
				input.nextLine();
			//}			
		}
	}

}

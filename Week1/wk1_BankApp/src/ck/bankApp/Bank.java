package ck.bankApp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args)
	{
		UserIO io = new UserIO();
		List<User> users = io.readUsers();
		int userSelection = mainBankUI();
		//receive input from mainBankUI(), pass to switch statement
		switch(userSelection)
		{
		case 1:
			//create new account
			//set loggedInUser to be the new entry in the list
			break;
		case 2:
			//login
			
			break;
		case 3:
			//write all users to file
			for(User u : users)
			{
				io.writeUser(u);
			}
			System.out.println("Users written to file.");
			System.out.println("Thank you. Good-bye.");
			System.exit(0);
		default:
			System.out.println("SOMETHING WENT WRONG");
			System.exit(1);
		}//switch
		
	}//main

	//first, load all the users into a list of user objects
	
	//login method
		//user provides username
		//check to see if username is present in list of users
			//if yes - make that the current user
				//start user interface for withdrawal, deposit, check balance, logout
			//if not found - error message and loop back to provide username
//======================================================
	public static int mainBankUI()
	{
		Scanner sc = new Scanner(System.in);
		int userInput=-1;
		boolean inputCheck = true;
		System.out.println("Please enter your selection:");
		System.out.println("============================");
		System.out.println("1 - create new user");
		System.out.println("2 - login");
		System.out.println("3 - exit");
		
		while(inputCheck)
		{
			try
			{
				userInput = sc.nextInt();
				if(userInput == 1 || userInput == 2 || userInput == 3)
				{
					System.out.println("selection successful");
					inputCheck = false;
				}else
				{
					System.out.println("--INVALID ENTRY--INVALID ENTRY--\n");
					System.out.println("Please enter your selection:");
					System.out.println("============================");
					System.out.println("1 - create new user");
					System.out.println("2 - login");
					System.out.println("3 - exit");
					sc.nextLine();
				}
			}catch(InputMismatchException ime)
			{
				System.out.println("--INVALID ENTRY--INVALID ENTRY--\n");
				System.out.println("Please enter your selection:");
				System.out.println("============================");
				System.out.println("1 - create new user");
				System.out.println("2 - login");
				System.out.println("3 - exit");
				sc.nextLine();
			}
		}//while
		sc.close();
		return userInput;
	}//mainBankUI
//=================================================
	
	public List<User> createNewUser(List<User> ul)
	{
		boolean inputCheck = true;
		String newUsername = null;
		User newUser = new User();
		System.out.println("========================");
		System.out.println("CREATE NEW USER");
		System.out.println("========================");
		//username
		while(!usernameCheck)
		{
			newUsername = sanitizeUsername();
			for(User u : ul)
			{
				if(newUsername.equals(u.getUsername()))
				{
					System.out.println("Username already taken");
				}else
				{
					newUser.setUsername(newUsername);
					usernameCheck = true;
				}//if-else
			}//for
		}//while - username
		
		//first name
		//last name
		//balance
		
		
		return ul;
	}//createNewUser
	
	public String sanitizeUsername()
	{
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String newUsername = null;
		System.out.println("Username must be between 1-10 characters and contain only letters and digits");
		System.out.println("Enter new username:");
		
		while(!valid)
		{
			newUsername = sc.nextLine();
			if(newUsername.length() >= 1 && newUsername.length() <= 10)
			{
				if(newUsername.matches("[A-Za-z0-9]*"))
				{
					System.out.println("Valid username entered. Checking against list of current users");
					valid = true;
				}else
				{
					System.out.println("Username must be between 1-10 characters and contain only letters and digits");
					System.out.println("Enter new username:");
					sc.nextLine();
				}
			}else
			{
				System.out.println("Username must be between 1-10 characters and contain only letters and digits");
				System.out.println("Enter new username:");
				sc.nextLine();
			}
		}//while
		
		sc.close();
		return newUsername;
	}//sanitizeUsername
	
	
	
}

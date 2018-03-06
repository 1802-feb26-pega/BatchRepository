package ck.bankApp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)
	{
		UserIO io = new UserIO();
		List<User> users = io.readUsers();
		int userSelection = mainBankUI();
		//receive input from mainBankUI(), pass to switch statement
		switch(userSelection)
		{
		case 1:
			users = createNewUser(users);
			User loggedInUser = users.get(users.size()-1);
			System.out.println(loggedInUser.getUsername());
			
			for(User u : users)
			{
				io.writeUser(u);
			}
			System.exit(0);
			
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
		
		sc.close();
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
		//Scanner sc = new Scanner(System.in);
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
		//sc.close();
		return userInput;
	}//mainBankUI
//=================================================
	
	public static List<User> createNewUser(List<User> ul)
	{
		User newUser = new User();
		boolean usernameCheck = false;
		String newUsername = null;
		String newFirstName = null;
		String newLastName = null;
		double newBalance = -1;
		
		System.out.println("========================");
		System.out.println("CREATE NEW USER");
		System.out.println("========================");
		//username
		while(!usernameCheck)
		{
			newUsername = sanitizeUsername();
			for(User u : ul)
			{
				if(newUsername.equals(u.getUsername())&&!newUsername.equals(null))
				{
					System.out.println("Username already taken");
				}else
				{
					System.out.println("Username accepted\n");
					newUser.setUsername(newUsername);
					usernameCheck = true;
				}//if-else
			}//for
		}//while - username
		
		//first name
		newFirstName = sanitizeFirstName();
		newUser.setFirstName(newFirstName);

		//last name
		newLastName = sanitizeLastName();
		newUser.setLastName(newLastName);
		
		//balance
		newBalance = sanitizeNewBalance();
		newUser.setBalance(newBalance);
		
		ul.add(newUser);
		
		for(User u : ul)
		{
			System.out.println(u.getUsername());
		}
		
		return ul;
	}//createNewUser
//=================================================
	public static String sanitizeUsername()
	{
		//Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String newUsername="";
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
					//sc.nextLine();
				}
			}else
			{
				System.out.println("Username must be between 1-10 characters and contain only letters and digits");
				System.out.println("Enter new username:");
				//sc.nextLine();
			}

		}//while
		
		//sc.close()
		
		return newUsername;
	}//sanitizeUsername
//=======================================
	public static String sanitizeFirstName()
	{
		//Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String newFirstName = null;
		System.out.println("First name must be between 1-12 characters and can only contain letters");
		System.out.println("Enter first name:");
		
		while(!valid)
		{
			newFirstName = sc.nextLine();
			if(newFirstName.length() >= 1 && newFirstName.length() <= 12)
			{
				if(newFirstName.matches("[A-Za-z]*"))
				{
					System.out.println("First name accepted.");
					valid = true;
				}else
				{
					System.out.println("First name must be between 1-12 characters and can only contain letters");
					System.out.println("Enter first name:");
					//sc.nextLine();
				}
			}else
			{
				System.out.println("First name must be between 1-12 characters and can only contain letters");
				System.out.println("Enter first name:");
				//sc.nextLine();
			}
		}//while
		//sc.close();
		return newFirstName;
	}//sanitizeFirstName
	//=======================================
		public static String sanitizeLastName()
		{
			//Scanner sc = new Scanner(System.in);
			boolean valid = false;
			String newLastName = null;
			System.out.println("Last name must be between 1-12 characters and can only contain letters");
			System.out.println("Enter last name:");
			
			while(!valid)
			{
				newLastName = sc.nextLine();
				if(newLastName.length() >= 1 && newLastName.length() <= 12)
				{
					if(newLastName.matches("[A-Za-z]*"))
					{
						System.out.println("Last name accepted.");
						valid = true;
					}else
					{
						System.out.println("Last name must be between 1-12 characters and can only contain letters");
						System.out.println("Enter last name:");
						//sc.nextLine();
					}
				}else
				{
					System.out.println("Last name must be between 1-12 characters and can only contain letters");
					System.out.println("Enter last name:");
					//sc.nextLine();
				}
			}//while
			//sc.close();
			return newLastName;
		}//sanitizeLastName
//===================================================
		public static double sanitizeNewBalance()
		{
			//Scanner sc = new Scanner(System.in);
			double newBalance = -1;
			boolean valid = false;
			
			while(!valid)
			{
				try
				{
					System.out.println("Enter your starting balance:");
					newBalance=sc.nextDouble();
					if(newBalance < 0)
					{
						System.out.println("--INVALID ENTRY--INVALID ENTRY--");
						System.out.println("Your starting balance cannot be negative");
						System.out.println("================================");
						sc.nextLine();
					}else
					{
						System.out.println("Balance accepted");
						valid = true;
					}//if-else
				}catch(InputMismatchException ime)
				{
					System.out.println("--INVALID ENTRY--INVALID ENTRY--");
					System.out.println("Please enter a NUMBER greater than 0");
					System.out.println("================================");
					sc.nextLine();
				}//try-catch
			}//while
			//sc.close();
			return newBalance;
		}//sanitizeNewBalance
	
}

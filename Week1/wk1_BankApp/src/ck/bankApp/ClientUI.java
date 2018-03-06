package ck.bankApp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClientUI {
	public static Scanner sc = new Scanner(System.in);
	
	public int topUI()
	{
		int userInput = -1;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("Make your selection:");
			System.out.println("1 - Create new account");
			System.out.println("2 - Login");
			System.out.println("3 - Exit");
			try
			{
				userInput = sc.nextInt();
				if(userInput == 1 || userInput == 2 || userInput == 3)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 1 | 2 | 3. Try again.\n");
					sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int input. Try again.\n");
				sc.nextLine();
			}//try-catch
		}//while
		return userInput;
	}//topUI
	
	//=======================================================
	
	public List<User> createNewUser(List<User> users)
	{
		User newPerson = new User();
		
		
		//validateUsername
		newPerson.setUsername(validateUsername(users));
		
		//validateFirstName
		newPerson.setFirstName(validateFirstName());
		
		//validateLastName
		newPerson.setLastName(validateLastName());
		
		//validateBalance
		newPerson.setBalance(validateBalance());
		
		//add new person to the list
		users.add(newPerson);
		
		
		return users;
	}//createNewUser
	
	//========================================================
	
	public String validateUsername(List<User> u)
	{
		String newU = "";
		boolean valid = false;
		boolean unique = true;
		
		//NEED TO MAKE SURE THE USERNAME HASNT BEEN USED BEFORE
		
		while(!valid)
		{
			System.out.println("Enter username: (1-10 characters, letters and #'s only)");
			newU = sc.nextLine();
			unique = true;
			if(newU.length()>=1 && newU.length()<=10)
			{
				if(newU.matches("[A-Za-z0-9]*"))
				{
					for(User a : u)
					{
						if(newU.equals(a.getUsername()))
						{
							unique = false;
							break;
						}//if
					}//for
					if(!unique)
					{
						System.err.println("\nUsername taken\n");
					}else
					{
						System.out.println("Good username: " + newU + "\n");
						valid = true;
					}//if
				}else
				{
					System.out.println("\nIllegal characters\n");
				}//if
			}else
			{
				System.out.println("\nBad length\n");
			}//if
		
		}//while
		
		return newU;
	}//validateUsername
	
	//=================================================
	
	public String validateFirstName()
	{
		String newF = null;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("Enter first name: (1-12 characters, letters only)");
			newF = sc.nextLine();
			if(newF.length() >= 1 && newF.length() <= 10)
			{
				if(newF.matches("[A-Za-z]*"))
				{
					System.out.println("Good first name: " + newF +"\n");
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}
			}else
			{
				System.out.println("\nBad length\n");
			}
			
		}//while
		return newF;
	}//validateFirstName
	
	//====================================================
	
	public String validateLastName()
	{
		String newL = null;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("Enter last name: (1-12 characters, letters only)");
			newL = sc.nextLine();
			if(newL.length() >= 1 && newL.length() <= 10)
			{
				if(newL.matches("[A-Za-z]*"))
				{
					System.out.println("Good last name: " + newL);
					valid = true;
				}else
				{
					System.out.println("\nIllegal characters\n");
				}
			}else
			{
				System.out.println("\nBad length\n");
			}
			
		}//while
		return newL;
	}//validateLastName
	
	//========================================================
	
	public double validateBalance()
	{
		double newB = 0;
		boolean valid = false;
		
		while(!valid)
		{
			System.out.println("Enter starting balance: (greater than 0)");
			try
			{
				newB = sc.nextDouble();
				if(newB>=0)
				{
					System.out.println("Good balance: " + newB);
					valid = true;
				}else
				{
					System.out.println("\nStarting balance must be greater than 0\n");
					sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-double entry\n");
				sc.nextLine();
			}//try-catch
		}//while
		
		
		return newB;
	}//validateBalance
	
}

package com.revature.FullStack;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



	public class HomeMenu{ 

		public static void main(String[] args) throws IOException {
	try
	{
		User U = new User();
		Update UP = new Update();
		
		
		char a ='n';
		Scanner scan = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("                                      Welcome to Smith Bank");
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------------------------------");
		int choice;
		
		do 
		{
			
		a='n';
		System.out.println("Please enter your choice from one of the following options");
		System.out.println("1.Create new account");
		System.out.println("2.Existing account");
		System.out.println("3.Delete account");
		System.out.println("4.Exit");
		System.out.println("----------------------------------------------------------------------------------------------------");
		choice = scan.nextInt();	
				if(choice>4 || choice <1)
				{
					System.out.println("You have entered an incorrect choice");
					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.println("Do you want to enter again? Y/N  :");
					a= scan.next().charAt(0);
					System.out.println("----------------------------------------------------------------------------------------------------");
				}
			
		}while
			(a=='y' || a =='Y');
			
		
		switch(choice)
		{
		case 1: U.NewUser();
		break;
		case 2: U.ExistingUser();
		break;
		case 3: U.DeleteAccount();
		break;
		case 4: System.out.println("Thank you");
		System.out.println("----------------------------------------------------------------------------------------------------");
		break;
		}
	}
	catch(InputMismatchException a)
	{
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("You have entered an invalid input");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
	}
	
	
	
	
	
	
}	

}

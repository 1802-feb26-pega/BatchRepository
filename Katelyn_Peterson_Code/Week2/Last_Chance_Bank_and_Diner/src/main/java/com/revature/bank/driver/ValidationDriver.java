package com.revature.bank.driver;

import java.util.Scanner;

public class ValidationDriver
{
	// User Variables
	static private Scanner userInput = new Scanner(System.in);
	
	// Number validation for Integers
	public static void validateInput(Scanner input, String num)
	{
		System.out.println("Enter " + num + ": ");
		
		while(!input.hasNextInt())
		{
			System.out.println("Please enter a number.");
			System.out.println("Enter " + num + ": ");
			input.nextLine();
		}
	}

	// Number validation for Doubles
	public static void validateInput(String num, Scanner input)
	{
		System.out.println("Enter " + num + ": ");
		
		while(!input.hasNextDouble())
		{
			System.out.println("Please enter a number.");
			System.out.println("Enter " + num + ": ");
			input.nextLine();
		}
	}
	
	// Negative Number validation
	public static boolean notNega(double amount)
	{
		if (amount >= 0)
		{
			return true;
		}
		
		System.out.println("Number is Negative");
		System.out.println("Nice try...better luck next time.");
		
		return false;
	}
	
	// Choice Validation
	public static int choiceVal(int option, int lower, int higher)
	{
        while(option < lower | option > higher)
        {
        	System.out.println("Very Funny.");
        	System.out.println("No, really, what do you want to do?");
        	validateInput(userInput, "Choice");
	        option = userInput.nextInt();
	        userInput.nextLine();
        }
        
        return option;
	}
}

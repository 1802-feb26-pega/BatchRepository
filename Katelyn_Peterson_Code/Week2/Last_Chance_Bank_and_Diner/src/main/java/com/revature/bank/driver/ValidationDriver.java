package com.revature.bank.driver;

import java.util.Scanner;

/**
* <h1> ValidationDriver </h1> 
* The ValidationDriver class includes a Scanner.
* 
* This class handles most of the validation and is primarily called by the MenuDriver class.
* 
* @author Katelyn Peterson
* @version 1.0
* @since 03-09-2018 
*
*/
public class ValidationDriver
{
	// User Variables
	static private Scanner userInput = new Scanner(System.in);
	
	// Number validation for Integers
	/**
	 * This is the method to validate Integer inputs.
	 * 
	 * @param input  This is the Scanner for user input.
	 * @param num  This is the input being tested.
	 */
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
	/**
	 * This is the method to validate Integer inputs. 
	 * 
	 * @param num  This is the input being tested.
	 * @param input  This is the Scanner for user input.
	 */
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
	/**
	 * This is the method to validate that an amount is not negative. 
	 * 
	 * @param amount  This is the input being tested.
	 */
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
	/**
	 * This is the method to validate menu inputs. 
	 * 
	 * @param option  This is the option typed by the user.
	 * @param lower  This is the lowest number that can be used.
	 * @param higher  This is the highest number that can be used.
	 */
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

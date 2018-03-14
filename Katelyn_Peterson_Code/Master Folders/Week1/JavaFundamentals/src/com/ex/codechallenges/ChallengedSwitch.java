package com.ex.codechallenges;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

// Katelyn and Sean

public class ChallengedSwitch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "Choice";
		Scanner input = new Scanner(System.in);
		int choice;
		double sqnum;
		
		System.out.println("Pick an option you will like: ");
		System.out.println("To get the square root of a number, type 1");
		System.out.println("To get today's date, type 2");
		System.out.println("To get a string into an array, type 3");
		
		validateInput(input, num);
		choice = input.nextInt();
		input.nextLine();
		
		switch(choice)
		{
		case 1: {
			System.out.println("Please input a number: ");
			num = "Number";
			validateInput(input, num);
			sqnum = (double)input.nextInt();
			input.nextLine();
			System.out.println(Math.sqrt(sqnum));
			break;
		}
		case 2: {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			System.out.println("Current date: " + today);
			break;
		}
		case 3: {
			System.out.println("Please input a string");
			String[] arr = input.nextLine().split("");
			for(int x = 0; x <arr.length; x++)
			{
				System.out.println(arr[x]);
			}
			break;
		}
		}

	}
	
	static void validateInput(Scanner input, String num)
	{
		System.out.println("Enter " + num + ": ");
		
		while(!input.hasNextInt())
		{
			System.out.println("Please enter an Integer.");
			System.out.println("Enter " + num + ": ");
			input.nextLine();
		}
	}

}

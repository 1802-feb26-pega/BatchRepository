package com.ex.assignment1;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class SwitchAssignment {
	private static Scanner sc;
	
	public static void main(String[] args) {
		System.out.println("You have three options:\n"
				+ "1. Find square root of a number.\n"
				+ "2. Display today's date.\n"
				+ "3. Split the string you would enter.\n"
				+ "Please enter your choice (1, 2, or 3): ");
		
		sc = new Scanner(System.in);
		int number = sc.nextInt();
		
		switch(number) {
			case 1: System.out.println("Please enter a number. You'll get its square root: ");
					number = sc.nextInt();
					System.out.println("The square root is " + Math.sqrt(number));
					break;
			case 2: Date date = Calendar.getInstance().getTime();
					System.out.println("Today's date is " + date);
					break;
			case 3: System.out.println("Please enter a string. You'll get an array of characters: ");
					//sc2 = new Scanner(System.in);
					String str = sc.next();
					String[] arr = str.split("");
					// System.out.println("The array is " + arr.toString());
					System.out.println("The array is ");
					for(int i = 0; i < arr.length; i++) {
						System.out.print(arr[i] + " ");
					}
		};
	}

}

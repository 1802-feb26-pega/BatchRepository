package com.ex.codechallenge;

import java.util.Scanner;

public class Zullers
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. Variables
		int q = 0;
		int m = 0;
		int j = 0;
		int k = 0;
		
		Scanner input = new Scanner(System.in);
		int year;
		
		// 2. Input
		//System.out.println("Enter year: ");
		
		/*while (!input.hasNextInt())
		{
			System.out.println("Please enter an Integer.");
			System.out.println("Enter variable: ");
			input.nextLine();
		}*/
		
		validateInput(input, "year");
				
		year = input.nextInt();
		input.nextLine();
		
		validateInput(input, "month");
		
		m = input.nextInt();
		input.nextLine();
		
		// Changing Jan/Feb
		if (m == 1)
		{
			m = 13;
			year--;
		}
		else if (m == 2)
		{
			m = 14;
			year--;
		}
		
		validateInput(input, "day");
		
		q = input.nextInt();
		input.nextLine();
		
		// 3. Calculation
		// Changing year to century and year
		k = year % 100;
		System.out.println(k);
		j = (year - k) / 100;
		System.out.println(j);
		System.out.println(m);
		
		int answer = ((q +((26 *(m + 1))/10) + k + (k/4) + (j/4) + (5 *j) )% 7);
		
		System.out.println(answer);
		
		// 4. Output
		if(answer == 0) {
			System.out.println("Day of week is Saturday");
		}
		else if(answer == 1) {
			System.out.println("Day of week is Sunday");
		}
		else if(answer == 2) {
			System.out.println("Day of week is Monday");
		}
		else if(answer == 3) {
			System.out.println("Day of week is Tuseday");
		}
		else if(answer == 4) {
			System.out.println("Day of week is Wednessday ");
		}
		else if(answer == 5) {
			System.out.println("Day of week is Thursday");
		}
		else {
			System.out.println("Day of week is Friday");
		}
		
		

	}
	
	static void validateInput(Scanner input, String date)
	{
		System.out.println("Enter " + date + ": ");
		
		while (!input.hasNextInt())
		{
			System.out.println("Please enter an Integer.");
			System.out.println("Enter " + date + ": ");
			input.nextLine();
		}
	}

}
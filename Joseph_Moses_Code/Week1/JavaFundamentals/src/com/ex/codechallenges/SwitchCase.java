package com.ex.codechallenges;

import java.time.LocalDate;
import java.util.Scanner;

public class SwitchCase {
	
	public static void main(String[] args) {
		
		Scanner is = new Scanner(System.in);
		
		System.out.println("Enter your case number 1-3:");
		int x = is.nextInt();
		is.nextLine(); //to consume the left over new line character
		
		switch(x) {
		
		case 1: System.out.println("Enter a number to find it's square root:");
				int y = is.nextInt();
				System.out.println(Math.sqrt(y));
				break;
		case 2: System.out.println(LocalDate.now());
				break;
		case 3: System.out.println("Enter a String to split into an array");
				String str = is.nextLine();
				
				String [] tokens = str.trim().split("([ .,;:_-])");
				
				for(int i = 0; i < tokens.length; i++){
					System.out.println(tokens[i]);
				}
				break;
		
		}
		
	}
	
}

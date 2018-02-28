package com.ex.assignment2;

import java.util.Scanner;

public class Zuellers {
	private static Scanner sc;
	int h;
	int q;
	int m;
	int k;
	static double j;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		System.out.println("Please enter year: ");
		int k = sc.nextInt();
		
		j = k/100;
				
		System.out.println("Please enter month: ");
		int m = sc.nextInt();
		
		System.out.println("Please enter day: ");
		int q = sc.nextInt();
		
		int dayOfWeek = (int) (q + ((26*(m+1))/10) + k + k/4 + j/4 + 5*j) % 7;
		
		System.out.println("Please enter year: ");
		
		switch (dayOfWeek) {
		case 0: System.out.println("Saturday");
				break;
		case 1: System.out.println("Sunday");
				break;
		case 2: System.out.println("Monday");
				break;
		case 3: System.out.println("Tuesday");
				break;
		case 4: System.out.println("Wednesday");
				break;
		case 5: System.out.println("Thursday");
				break;
		case 6: System.out.println("Friday");
				break;
		};


	}

}

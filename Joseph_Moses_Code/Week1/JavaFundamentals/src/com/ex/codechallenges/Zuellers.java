package com.ex.codechallenges;

import java.util.Scanner;

public class Zuellers {

	public enum Day {Saturday, Sunday, Monday, Tuesday, Wednesday, Thursday, Friday}
	
	public static Day ZuellersAlgorithm(int year, int month, int day) {
		
		if(month == 1 || month == 2) {
			month += 12;
			year--;
		}
		
		int century = (int)Math.ceil(year/100);
		
		int yearOfCentury = year%100;
		
		int h = (day + ((26 * (month +1))/10) + yearOfCentury + (yearOfCentury/4) + (century/4) + (5 * century)) % 7;
	
		return Day.values()[h];
	}
	
	public static void main(String[] args) {
		
		Scanner is = new Scanner(System.in);
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		System.out.println("Enter year:");
		year = is.nextInt();
		
		System.out.println("Enter month:");
		month = is.nextInt();
		
		System.out.println("Enter day:");
		day = is.nextInt();
		
		Day d = ZuellersAlgorithm(year, month, day);
		
		System.out.println("The day of the week is: " + d.name());
	}
}

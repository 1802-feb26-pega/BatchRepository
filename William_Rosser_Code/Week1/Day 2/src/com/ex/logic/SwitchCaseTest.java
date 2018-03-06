package com.ex.logic;

import java.util.Scanner;

public class SwitchCaseTest {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		boolean go = true;
		while (go) {
			System.out.println("1: Square Root\n2: Show Day of Date\n3:Split a String\n4: Quit");
			System.out.println("Pick Mode:");
			int i = 0;
			while (i == 0) {
				try {
					sc = new Scanner(System.in);
					sc.reset();
					i = sc.nextInt();
					if (i > 4 || i <= 0) {
						i = 0;
					}
				} catch (Exception e) {
					System.out.println(e);
					System.out.println("Please enter a valid number.");
				} finally {
					if (i < 1 || i > 4) {
						System.out.println("Enter a number between 1 and 4 for the mode.");
						System.out.println("1: Square Root\n2: Show Date\n3:Split a String\n4: Quit");
					}
				}
			}
			String result = "";
			switch(i) {
				case 1: result += squareRoot(); break;
				case 2: result += date(); break;
				case 3: result += splitString(); break;
			default: go = false; return;
			}
			System.out.println("Result: " + result);
			i=0;
		}
	}

	private static double squareRoot() {
		return Math.sqrt(promptDouble("Enter a number to find the square root: ", "Enter a VALID number."));
	}
	
	private static String date() {
		int m = 0;
		while (m > 13 || m <= 0) {
			m = (int) promptDouble("Enter the month.","Enter a valid month.");
		}
		int q = 0;
		int qm = 0;
		switch(m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: qm = 31; break;
		case 2: qm = 29; break;
		default: qm = 30; break;
		}
		m = (m <= 2) ? m + 12: m;
		while (q <= 0 || q > qm) { 
			q = (int) promptDouble("Enter the day of the month.","Enter a valid Day.");
		}
		int year = (int) promptDouble("Enter the year.", "Enter a valid year.");
		if (m > 12) year--;
		int j = year/100;
		int k = year % 100;
		System.out.println(q + " " + m + " " + j + " " + k);
		
		int h = (int) (q + 26.0*(m+1.0)/10.0 + k + k/4.0 + j/4.0 + 5*j) % 7;
		
		switch(h) {
		case 0: return "Saturday";
		case 1: return "Sunday";
		case 2: return "Monday";
		case 3: return "Tuesday";
		case 4: return "Wednesday";
		case 5: return "Thursday";
		default: return "Friday";
		}
	}
	
	private static String splitString() {
		sc.nextLine();
		System.out.println("Enter a string to split:");
		String s = sc.nextLine();
		System.out.println(s);
		String sbox[] = s.split("\\s"); // Splits the string into an array
		String result = "[";
		for (String item : sbox) {
			result += item +", ";
		}
		return result.substring(0,result.length()-2) + "]";
	}
	
	private static double promptDouble(String prompt, String err) {
		boolean go = true;
		while (go) {
			System.out.print(prompt);
			try {
				return sc.nextDouble();
			} catch (Exception e) {
				System.out.println(err);
			}
		}
		return 0.0;
	}
}

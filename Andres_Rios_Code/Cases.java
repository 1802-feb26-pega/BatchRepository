package com.ex.challenges;

import java.util.Scanner;
import java.util.Date;


public class Cases {
	static Scanner scan = new Scanner(System.in);
	
	public static void caseNumber() {
		int n = scan.nextInt();
		switch(n) {
		case 1:
			int a = new Scanner(System.in).nextInt();
			System.out.println(Math.sqrt(a));
			break;
		case 2:
			Date obj = new Date();
			System.out.println(obj);
			break;
		case 3:
			String c = new Scanner(System.in).nextLine();
			String arr[] = c.split("");
			for (String temp: arr) {
				System.out.println(temp);
			}
			break;
		}
		
		
	}
	public static void algorithm() {
		System.out.println("Enter year: ");
		int year = scan.nextInt();
		int k = year % 100;
		System.out.println("Enter month: ");
		int m = scan.nextInt();
		if( m == 1) {
			m = 13;
			k--;
		} else if(m == 2) {
			m = 14;
			k--;
		}
		System.out.println("Enter day: ");
		int q = scan.nextInt();
		int j = 21 * (k/100);
		int h = (q + (26 * (m+1))/10 + k + k/4 + j/4 + 5*j) % 7; 
		if (h==0) {
			System.out.println("Saturday");
		} else if(h==1) {
			System.out.println("Sunday");
		} else if(h==2) {
			System.out.println("Monday");
		} else if(h==3) {
			System.out.println("Tuesday");
		} else if(h==4) {
			System.out.println("Wednesday");
		} else if(h==5) {
			System.out.println("Thursday");
		} else if(h==6) {
			System.out.println("Friday");
		}
		
	}
	public static void main(String [] args) {
		caseNumber();
		algorithm();
	}

}

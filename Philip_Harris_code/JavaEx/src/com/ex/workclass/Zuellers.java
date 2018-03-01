package com.ex.workclass;

import java.util.Scanner;

public class Zuellers {
	
	static int h, j, k,q,m;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Day of the week is: " + getNextDay());
	}
	
	public enum Day {
		 SATURDAY,SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
	    THURSDAY, FRIDAY
	}
	
	public enum Months{
		
		JAN, FEB, MAR, APR, MAY, JUN,
		JUL, AUG, SEPT, OCT, NOV, DEC
	}
	public static int checkInput(int input) {
		
		int result;
		
		 while(true) {
			  
			  try  
			  {  
				 result = input;  
				 break;
			  }  
			  catch(NumberFormatException nfe)  
			  {  
				  System.out.println("Please enter a number");
				  System.out.println();
				  //num = scan.nextLine();
			  }  
			}
		  
		
		return 0;
		
	}
	
	public static String getNextDay() {
		
		Scanner scan = new Scanner(System.in);
		
		
		Day[] weekDay = Day.values();
		Months[] months = Months.values();
				
		
		
		int year = checkInput(Integer.parseInt(scan.nextLine()));
		m = Integer.parseInt(scan.nextLine());
		 if (m == 1 || m == 2) {
		    	m = m + 12;
		    	year = year - 1;
		    }
		j = year / 100;
		k = year % 100;
		q = Integer.parseInt(scan.nextLine());
		
		int a = (26*(m+1))/10;
		int b = k/4;
		int c = j/4;
		int d = 5*j;
		
		h = (q + a + k + b + c + d) % 7;
		
		String result = weekDay[h].toString();
		
		
		
		return result;
		
	}

}

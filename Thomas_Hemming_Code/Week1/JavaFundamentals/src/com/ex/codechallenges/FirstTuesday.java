package com.ex.codechallenges;
import java.util.Scanner;  
import java.util.Date;

public class FirstTuesday {
	
	public static void main(String args[]){  
		/*Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter 1-3:");
		System.out.println();
		String x = scan.nextLine();
		while(true) {
			if(x.contentEquals("1")) break;
			else if(x.contentEquals("2")) break;
			else if (x.contentEquals("3")) break;
			System.out.println("Please enter a number 1 - 3");
			System.out.println();
			x = scan.nextLine();
		}
		scan.close();
		
		switch(x) {
		case "1": sqrt(); break;
			      
		case "2": getDate(); break; 
		          
		case "3": splitString(); break;
			
	    default: break;
		} */
		System.out.println("square root");
		sqrt();
		System.out.println("get day from date");
		getDate();
		System.out.println("get today's date");
		getToday();
		System.out.println("split string");
		splitString();
	}  

	static void sqrt() {
		Scanner scan = new Scanner(System.in);
		  
		double result;
		System.out.println("Enter in a number to find the squareroot of it:");
		System.out.println();			  
		String num = scan.nextLine();
		  
		  
		  
		while(true) {
			  
			try {  
				 result = (double) Math.sqrt(Double.parseDouble(num));  
				 break;
			} catch(NumberFormatException nfe)  
			{  
				 System.out.println("Please enter a number");
				 System.out.println();
				 num = scan.nextLine();
			}  
		}
		
		System.out.println("Your number was "+ num + ". The squareroot is " + result);
		scan.close();
		
	}
	
	static void getDate() {
		Scanner scan = new Scanner(System.in);
		  
		int result;
		System.out.println("Enter year:");
		System.out.println();			  
		String num = scan.nextLine();
		
		while(true) {
			  
			try {  
				 result = (int) Math.sqrt(Integer.parseInt(num));  
				 break;
			} catch(NumberFormatException nfe)  
			{  
				 System.out.println("Please enter a number:");
				 System.out.println();
				 num = scan.nextLine();
			}  
		}
	    int year = Integer.parseInt(num);
	    
	    System.out.println("Enter month:");
		System.out.println();			  
		num = scan.nextLine();
		
		while(true) {
			  
			try {  
				 result = (int) Math.sqrt(Integer.parseInt(num));  
				 break;
			} catch(NumberFormatException nfe)  
			{  
				 System.out.println("Please enter a number:");
				 System.out.println();
				 num = scan.nextLine();
			}  
		}
	    int month = Integer.parseInt(num);
	    if (month == 1 || month == 2) {
	    	month = month + 12;
	    	year = year - 1;
	    }
	    System.out.println("Enter day:");
		System.out.println();			  
		num = scan.nextLine();
		
		while(true) {
			  
			try {  
				 result = (int) Math.sqrt(Integer.parseInt(num));  
				 break;
			} catch(NumberFormatException nfe)  
			{  
				 System.out.println("Please enter a number:");
				 System.out.println();
				 num = scan.nextLine();
			}  
		}
	    int day = Integer.parseInt(num);
	    
	    int q = day;
	    
	    int m = month;
	    
	    int j = Math.abs(year/100);
	    
	    int k = Math.abs(year%100);
	    
	    
	    
	    
		
		//System.out.println(year + " " + month + " " + day);
		
		int dayNum = (q + ((26*(m+1))/10) + k + (k/4) + ((j/4) + (5*j))) % 7;
		
		//System.out.println(dayNum);
		
		String[] x = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		
		System.out.println("Day of the week is " + x[dayNum] + ".");
		scan.close();
		
	}
	
	static void getToday() {
		Date today = new Date();
		today.getDay();
		System.out.println(today);
	}
	
	static void splitString() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a String:");
		System.out.println();			  
		String string = scan.nextLine();
		scan.close();
		
		String[] x = string.split("\\s+");
		
		for (String i : x) {
			System.out.println(i);
		}
		
		
	}
	
	

}

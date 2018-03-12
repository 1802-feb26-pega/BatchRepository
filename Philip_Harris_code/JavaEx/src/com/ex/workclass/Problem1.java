package com.ex.workclass;

import java.util.Scanner;
import java.util.Date;

public class Problem1 {
 public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	
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
	switch(x) {
		case "1": 
			      int result;
				  System.out.println("Enter in a number to find the squareroot of it:");
				  System.out.println();			  
				  String num = scan.nextLine();
				  
				  
				  
				  while(true) {
					  
					  try  
					  {  
						 result = (int) Math.sqrt(Integer.parseInt(num));  
						 break;
					  }  
					  catch(NumberFormatException nfe)  
					  {  
						  System.out.println("Please enter a number");
						  System.out.println();
						  num = scan.nextLine();
					  }  
					}
				  
				  System.out.println("Your number was "+ num + ". The squareroot is " + result);
				  break;
	case "2": 	  
		          System.out.println(getDate());
		          break;
	case "3":	
				  System.out.println("Please give me a word!");
				  System.out.println();
				  String word = scan.nextLine();
				  Character[] ray = new Character[word.length()];
				  
				  for(int i = 0; i < word.length();i++) {
					  ray[i] = word.charAt(i);
					  System.out.println(ray[i]);
				  }
				  break;
				   
				}
	}
 
static Date getDate() {
	 Date today = new Date();
	 
	return today;
 }
}
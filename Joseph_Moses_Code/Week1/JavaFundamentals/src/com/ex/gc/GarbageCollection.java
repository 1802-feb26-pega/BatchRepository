package com.ex.gc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GarbageCollection {

	public static void main(String[] args) {
		/*String a = new String("jadshgakjs");
		String b = new String("jashg");
		
		String c = "hello";
		
		a = null;
		
		//request jvm to run garbage collector
		System.gc();
		
		b = null;
		
		String d = "akjsdg";
		
		String f = "lkjasdgh";
		
		
		//another way to request garbage collector
		Runtime.getRuntime().gc();
		
		System.out.println("at end of main");
		System.out.println("------------");
		
		for(int i = 0; i < 500; i  = i + 5) {
			System.out.print(i);
		}
		*/
		
		

		System.out.println(solveWordProblem("What is 1 plus 1?"));
		}
		
static int solveWordProblem(String string) {
	
	int output = 0;
	ArrayList<Integer> numbers = new ArrayList<>();
	String operation = null;
	
	string = string.replaceAll("[^a-zA-Z0-9- ]", "");
	System.out.println(string);
	Scanner scan = new Scanner(string);
	scan.useDelimiter(" ");
	
	while(scan.hasNext()) {
		
		if(scan.hasNextInt()) {
			numbers.add(scan.nextInt());
		}
		else if(scan.hasNext("plus")) {
			operation = scan.next(); 
		}
		else if(scan.hasNext("minus")) {
			operation = scan.next(); 
		}
		else if(scan.hasNext("multiplied")) {
			operation = scan.next(); 
		}
		else if(scan.hasNext("divided")) {
			operation = scan.next(); 
		}
		else {
			scan.next();
		}	
	}
	scan.close();
	System.out.println(numbers);
	System.out.println(operation);
	if(operation.equals("plus")) {
		output = numbers.get(0) + numbers.get(1);
	}
	else if(operation.equals("minus")) {
		output = numbers.get(0) - numbers.get(1);
	}
	else if(operation.equals("multiplied")) {
		output = numbers.get(0) * numbers.get(1);
	}
	else if(operation.equals("divided")) {
		output = numbers.get(0) / numbers.get(1);
	}
	
	return output;
	
	
}



	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 * 
	 * finalize method is called on an object once before it is garbage collected
	 */
	@Override
	protected void finalize() throws Throwable{
		System.out.println("garbage collector is called on " + this);
	}
}

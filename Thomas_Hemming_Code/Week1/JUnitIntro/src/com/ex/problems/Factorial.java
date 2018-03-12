package com.ex.problems;

public class Factorial {
	
	public static void main(String[] args) {
		System.out.println(factorial(0));
	}
	
	public static int factorial(int a) {
		//assert a != 0;
		int i,fact=1;    
		for(i=1;i<=a;i++){    
			fact=fact*i;    
		}    	
		return fact;
	}
}

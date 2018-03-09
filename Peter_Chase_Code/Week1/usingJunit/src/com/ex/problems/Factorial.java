package com.ex.problems;

public class Factorial {
	
	public static void main(String[] args) {
		System.out.println(new Factorial().factorial(0));
	}
	
	public int factorial(int n) {		
		int product = n;
		
		for (int i = n-1; i > 1; i--) {
			product *= i;
		}
		
		return product;
	}

}

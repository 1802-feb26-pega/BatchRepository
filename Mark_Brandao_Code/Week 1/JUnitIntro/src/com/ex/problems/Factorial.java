package com.ex.problems;

public class Factorial {
	
	public int factorial(int n) {	
		int prod = 1;
		for(int i = n; i> 1; i--) {
			prod *= i;
		}
		return prod;
	}
	
}

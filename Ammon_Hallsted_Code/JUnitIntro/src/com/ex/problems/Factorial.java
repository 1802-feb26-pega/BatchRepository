package com.ex.problems;

public class Factorial {
	
	public static void main(String[] args) {
		System.out.println(factorial(0));
	}
	public static int factorial(int n) {
		assert n != 0;
		int prod = 1;
		for(int i = n; i > n; i--) {
			prod *= i;
		}
		return prod;
	}
}

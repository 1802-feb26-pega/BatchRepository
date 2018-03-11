package com.ex.problems;

public class Factorial {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	
	public int factorial(int n) {
		int prod = 1;
		for (int i = n; i > 1; i--) {
			prod *= i;
		}
		return prod;
	}
	
//	public static void main(String[] args) {
//		Factorial factorial = new Factorial();
//		System.out.println(factorial.factorial(0));
//	}

}

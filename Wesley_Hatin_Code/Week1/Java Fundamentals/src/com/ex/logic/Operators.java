package com.ex.logic;

public class Operators {
	/*A Java operator is a special symbol that can be applied to a set of 
	 * variables, values, or literals - referred to ass operands - and that rreturns a result.
	 * 
	 * There are Unary, Binary, and Ternary operators - with 1, 2, and 3 
	 * operands, respectively.
	 */

	//UNARY 
	
	//post unary operators
	void postUnary(int count) {
		count ++;
		count --;
	}
	//pre unary operators
	void preUnary(int count) {
		count ++;
		count --;
	}
	
	// 2's complement: n = -(n+1)
	/*
	 * 11011100 = 220
	 * 
	 * Bitwise complement is denoted by ~
	 * The compiler, however gives us the 1's complement of the number 
	 * for any int n, 2's complement of n = -(n+1)
	 */
	static int complement(int x) {
		x = +4;
		return x;
	}
	
	//BINARY
	
	static int binary(int a, int b) {
		//in order from left to right
		a = a * 2;
		b = 2 / b;
		int even = b % 2;
		return even;
	}
	
	static int leftShift(int num, int shift) {
		return num << shift;
	}
	
	public int rightShift(int num, int shift) {
		return num >> shift;
	}
	
	public int unsignedRightShift(int num, int shift) {
		return num >>> shift;
	}
	
	//relational > < >= <= instance of
	static int relational(int a, int b) {
		String hi = "hi";
		if(hi instanceof String) {
			System.out.println("hi is a string");
		}
		return 0;
	}
	
	//equals
	public boolean equals(int a, int b) {
		if(a==b) {
			return true;
		}
		else return false;
	}
	
	/* Bitwise OR is a binary operator denoted by |.
	 * It compares corresponding bits of two operands, if either of
	 *  the bits is 1, it gives 1; if not, it gives 0, for example:
	 *  
	 *  12 = 00001100   |
	 *  25 = 00011001
	 *  
	 *  	 00011101 = 29
	 */
	
	static int bitwiseOR(int a, int b) {
		return a | b;
	}
	
	static int bitwiseAND(int a, int b) {
		return a & b;
	}
	
	public String shortCircuit(boolean first, boolean second) {
		if (first && second) {
			return "if first is false, second is not evaluated because the "
					+ "statement cannot be true regardless of whether or not "
					+ "second is true.";
		}
		else if (first || second) {
			return "if first is true, second is not evaluated because the OR "
					+ "will always be true regardless of the value of second.";
		}
		else return null;
	}
	
	//TERNARY --> [expression] ? (if yes) : (if no)
	static String ternary(int x) {
		return x > 10 ? "x is greater than 10" : "x is less than 10";
	}
	
	public static void main(String[] args) {
		int x = 5;
		System.out.println(x);
		System.out.println(x++);
		System.out.println(x);
	}
}

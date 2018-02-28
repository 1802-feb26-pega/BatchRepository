package com.ex.logic;

public class Operators {
	/*A Java operator is a special symbol that can be applied to a set of
	 * variables, values, or literals - referred to as operands - and that
	 * returns a result
	 * 
	 * There are Unary, Binary, and Ternary operators - with 1, 2, and 3
	 * operands, respectively
	 * 
	 */
	
	//UNARY
	
	//post-unary operators
	void postUnary(int count) {
		count++; // count = count + 1
		count--; // count = count - 1
	}
	
	void preUnary(int count) {
		++count;
		--count;
	} 
	
	// ~ 2's complement n = -(n+1)
	static int complement(int x) {
		return ~x;
	}
	
	static void moreOperators(int x) {// in order from left to right
		int y = -x;
		y = +4;
		boolean a = !true;
	}
	
	//BINARY
	
	static int binary(int a, int b) { // in order from left to right
		a = a * 2;
		b = 2 / a;
		int even = b % 2;
		return even;
	}
	
	//shift operators - laft to right << >> >>>
	/*Signed Left Shift is denoted by <<
	 * it shifts a bit pattern to the left by a certain number of specified bits,
	 * and zero bit
	 * 212 =		11010100
	 * 212 << 1 = 	
	 * 
	 */
	static int leftShift(int num, int shift) {
		return num << shift;
	}
	
	static int rightShift(int num, int shift) {
		return num >> shift;
	}
	
	static int unsignedRightShift(int num, int shift) {
		return num >>> shift;
	}
	
	//relational > < >= <= instanceof
	static int relational(int a, int b) {
		String hi = "hi";
		if(hi instanceof String) {
			System.out.println("hi is a string");
		}
		return 0;
	}
	
	//equals
	public boolean equals (int a, int b) {
		if(a == b) return true;
		else return false;
	}
	
	/*
	 * Bitwise OR is a binary operator denoted by |
	 * It compares corresponding bits of two operands. if either of
	 * the bits is 1, it gives 1. if not it gives 0. for example
	 * 
	 * 12 = 0000110 |
	 * 25 = 00011001
	 * 
	 * 		00011101 = 29
	 */
	
	static int bitwiseOR(int a, int b) {
		return a | b;
	}
	
	static int bitwiseAND(int a, int b) {
		return a & b;
	}
	
	/*
	 * 
	 */
	public String shortCiruit(boolean first, boolean second) {
		if(first && second) {
			return "if the first is false, second is not evalutated because the "
					+ "statement cannot be true regardless of whether or not"
					+ "second is true";
		} else if(first || second) {
			return "first is true, second is not evaluated becasue the OR "
					+ "wil always be true regardless of the value of the second";
		} else return null;
	}
	
	//TERNARY --> [expression] ? [if yes] : [if no]
	static String ternary(int x) {
		return (x > 10) ? ( x < 200 ? "x less than 200": " ") : "x is less than 10";
	}
	
	public static void main(String[] args) {
		
		int x = 5;
		System.out.println(x);
		System.out.println(x++);
		System.out.println(x);
		
	}
}

package com.ex.logic;

public class Operators {
	/* A Java operator is a special symbol that can be applied to a set of 
	 * variables, values, or literals - referred to sa operands - and that
	 * returns a result
	 * 
	 * There are Unary, Binary, and ternary operators - with 1, 2, and 3 
	 * operands, respectively
	 * 
	 */

	//UNARY
	
	//Post-unary operators
	static void postUnary (int count) {
		count++; // count = count + 1
		count--; // count = count - 1
	}
	
	//Pre-unary operators
	static void preUnary(int count) {
		++count;
		--count;
	}
	
	static int complement(int x) {
		return ~x;
	}
	
	static void moreOperators(int x) { // in order from left to right
		int y = -x;
		y = +4;
		boolean a = !true;
	}
	
	//BINARY
	static int binary(int a, int b) { // in order from left to right
		a = a * 2;
		b = 2 / a; 
		int even = b % 2 ;
		return even;
	}
	
	//shift operators = left to right << >> >>>
	/* Signed Left t shifts a bit pattern to the left by a certain number of specified bits,
	 * and zero bits are shifted into the low order positions
	 * 
	 * 212      = 11010100
	 * 212 << 1 = 110101000   = 424
	 */
	
	
	/* Signed right shift is denoted by >>
	 * Shifts a bit pattern to the right
	 * If the number is a 2's complement signed number, the sign bit 
	 * is shifted to the high order positions
	 * 
	 */
	public int rightShift(int num, int shift) {
		return num >>shift;
	}
	
	public int leftShift(int num, int shift) {
		return num >>> shift;
	}
	
	//relational - > < >= <= instanceOf
	static int relational(int a, int b) {
		String hi = "hi";
		if(hi instanceof String) {
			System.out.println("hi this is a string");
		}
		
		return 0;
	}
	
	//equals
	public boolean equals (int a, int b) {
		if (a == b) { return true; }
		else { return false; }
	}
	
	/* Bitwise OR is a binary operator denoted by |
	 * It compares correspodning bits of two operands. If either of
	 * the bits is 1, it gives 1. f not it gives 0. For example
	 * 
	 * 12 = 00001100   |
	 * 25 = 00011001 
	 * 
	 * 00011101 = 29
	 * 
	 */
	
	static int bitwiseOR(int a, int b) {
		return a | b;
	}
	
	static int bitwiseAND(int a , int b) {
		return a & b;
	}
	
	/* Short circuit operators
	 * doesn't necessarily evaluate all of its operands
	 * https://users.drew.edu/bburd/JavaForDummies4/ShortCircuitEval.pdf
	 */
	public String shortCircuit(boolean first, boolean second) {
		if (first && second) {
			return "if first is false, second is not evaluated because the "
					+ "statement cannot be true regardless of whether or not"
					+ "second is true";
		} else if (first || second ) {
			return "if first is true, second is not evaluated because the OR " 
					+ "will always be true regardless of the value of second";
		} else { return null; }
		
	}
	
	//TERNARY --> [expression] ? [if yes] : [if no]
	static String ternary(int x) {
		//return x > 10 ? "x is greater than 10" : "x is less than 10";
		
		//can nest them
		return (x > 10) ? (x < 200 ? "x less than 200" : "no" )  : "x is less than 10";
	}
	
	public static void main(String[] args) {
		
		
		
		
	}

}

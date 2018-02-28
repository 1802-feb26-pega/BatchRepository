package com.ex.logic;

public class Operators {
	/* A Java operator is a special symbol that can be applied to a set of variables,
	 * values, or literals - referred to as operands - and that returns a result
	 * 
	 * There are Unary, Binary, and Ternary operators - with 1, 2, and 3 operands, respectively
	 */
	void postUnary(int count) {
		count++; // count = count + 1
		count--; // count = count - 1
	}
	
	void preUnary(int count) {
		++count; // count = count + 1
		--count; // count = count - 1
	}
	
	// ~ 2's complement n = -(n+1)
	/*
	 * Bitwise complement is denoted by ~
	 * It inverts the bit pattern 
	 * 
	 * 35 = 00100011
	 * ~
	 * 		11011100 = 220
	 * 
	 * The complier, however, gives us the 2s complement of the number
	 * For any int n, 2's complement of n = -(n+1)
	 * 
	 * If placed in front of a string, it will 
	 */
	public static void main(String[] args) {
		// Unary
		
		//post-unary operators
		
		
		//int x = 5;
		//int y = 10;
		//int z = y ^ x * 2 / 19 % 2 >> 3;
	}

}

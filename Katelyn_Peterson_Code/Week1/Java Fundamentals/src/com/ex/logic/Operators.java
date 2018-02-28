package com.ex.logic;

public class Operators
{
	 /*
	  * Java operator is a special symbol that can be applied to a set of 
	  * variables, values, or literals - referred to as operands - and that
	  * returns a result.
	  * 
	  * There are Unary, Binary, and Ternary operators - with 1, 2, and 3
	  * operands, respectively.
	  */
	
	// Unary
	
	// Post-unary operators
	void postUnary(int count)
	{
		// count = count + 1
		count++;
		
		// count = count - 1
		count--;
	}
	
	// Pre-unary operators
	void preUnary(int count)
	{
		
		++count;
		
		
		--count;
	}
	
	// - 2's compliment n = -(n+1)
	/*
	 * Bitwise complement is denoted by ~
	 * It inverts the bit pattern
	 * 
	 * 35 = 00100011
	 * 
	 */
	static int complement(int y)
	{
		return ~y;
	}
	
	static void moreOperator(int x)
	{
		int y = -x;
	}
	
	// Binary
	
	static int binary(int x, int y)
	{
		x = x * 2;
		y = y / x;
		int even = y % 2;
		return even;
	}
	
	static int leftShift(int num, int shift)
	{
		return num << shift;
	}
	
	static int unsignedRightShift (int num, int shift)
	{
		return num >>> shift;
	}
	
	// relational - > < >= <= instanceof
	
	static int relational(int x, int y)
	{
		String hi = "hi";
		if (hi instanceof String)
		{
			System.out.println(hi);
		}
		
		return x;
	}
	
	// equals
	public boolean equals(int x, int y)
	{
		if (x == y) return true;
		else return false;
	}
	
	static int bitwiseOR(int x, int y)
	{
		return x | y;
	}
	
	static boolean or()
	{
		int x = 10;
		if (x > 10 | x < 5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	static int bitwiseAND(int x, int y)
	{
		return x & y;
	}
	
	public String shortCiruit(boolean first, boolean second)
	{
		if (first && second)
		{
			return "If first is false, second is not evaluated because the "
					+ "statement cannot be true regardless of whether or not"
					+ " second is true";
		}
		else if (first || second)
		{
			return "If first is true, second is not evaluated because the OR"
					+ " will always be true regardless of the value of second";
		}
		else return null;
	}
	
	// Ternary --> [expression] ? [if yes] : [if no]
	static String ternary (int x)
	{
		return (x > 10) ? (x < 200 ? "x less than 200" : "no ") : "x is less than 10";
	}
	
	public static void main(String[] args)
	{
		/*int x = 5;
		int y = 10;
		int z = y * x * 2 / 19 % 2 >> 3;*/
		
		int x = 5;
		
		System.out.println(x);
		System.out.println(++x);
		System.out.println(x++);
	}
}

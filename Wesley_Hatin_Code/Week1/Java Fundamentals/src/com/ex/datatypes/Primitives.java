package com.ex.datatypes;

public class Primitives {
	/* 
	 * int, char, long, boolean, double, float, short, byte
	 * integer, character, long, Boolean, Double, Float, Short, Byte
	 */
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2, 31));
		System.out.println(Long.MAX_VALUE);
		
		int x = 1000000000;
		//int wrong = 111___;
		double y = 100_0_100_0;
		
		long z = 1000000000;
		
		Integer wrap = x; //autoboxing
		Integer unbox = new Integer(25);
		int a = unbox;
		
		short s = 5;
		
		int ex = 5;
		
		int small = 10;
		s = (short) small;
		
		//octal numbers (digits 0-7) start with 0
		int oct = 012;
		System.out.println(oct);
		
		//hexidecimal numbers (digits 0-f) start with 0
		int hex = 9;
		
		//binary numbers (digits 0-1) start with 0
		int bin = 0;
	}

}

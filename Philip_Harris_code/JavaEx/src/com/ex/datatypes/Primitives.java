package com.ex.datatypes;

public class Primitives {
	
	/* 
	 * int, char, long, double, boolean, short, byte, float
	 * Integer, Character, Long, Boolean, Double Float short and Byte are wrappers for the raw data types
	 * 
	 */
	
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2, 31));
		System.out.println(Long.MAX_VALUE);
		
		
		// _ = , in terms of numbers
		int x = 1_000_000_000;
		//int wrong = _111___;
		double y = 100.0_000_000;
		//double no = 100._100;
		
		long z = 10000000000000L;
		
		Integer wrap = x;//autoboxing
		Integer unbox = new Integer(25);
		int a = unbox;
		
		short s = 5;
		
		int ex = s;
		
		int small = 10;
		s = (short) small;
		
		//octal numbers(digits 0 -7)
		int oct = 017;
		// 8 ^1 8^0
		System.out.println(oct);
		
		//hex(0-9, A-F) - 0x or 0X
		
		int hex = 0xFF;
		int h2 = 0XA23b;
		
		System.out.println(h2);
		
		//Binary (0,1) -0b or 0B
		
		
		
		int
		bin = 0b100100100101;
		int b2 = 0B001001001001;
		
		System.out.println(b2);
		
	}
	
	
	
}

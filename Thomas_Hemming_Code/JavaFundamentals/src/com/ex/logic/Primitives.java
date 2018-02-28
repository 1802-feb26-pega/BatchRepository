package com.ex.logic;

public class Primitives {
	/*
	 * int, char, long, boolean, double, float, short, byte
	 * Integer, Character, long, Boolean, Double, Float, Short, Byte
	 */
	
	public static void main (String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2, 31));
		System.out.println(Long.MAX_VALUE);
		
		int x = 1_000_000_000; // can add underscores to make it easier to read
	 // int wrong = _111___; doesn't work
		double y = 100.0_100_0;
	 // double no = 100._100; doesn't work
		
		long z = 1000000000000000000L; // have to add L for long to compile
		
		Integer wrap = x; //autoboxing
		Integer unbox = new Integer(25);
		int a = unbox;
		
		short s = 5;
		int ex = s;
		
		int small = 10;
		s = (short) small;
		
		//octal numbers (digits 0-7, base 8) - start with 0
		int oct = 021;
		// 8^1 8^0
		System.out.println(oct);
		
		//hex (0-9,A-F, base 16) - start with 0x OR 0X
		int hex = 0xFF;
		int hex2 = 0XA23B;
		System.out.println(hex + " " + hex2);
		
		//binary (0,1) - 0b or 0B
		int bin = 0b01010101010101;
		int bin2 = 0B0101010101010111;
		System.out.println(bin + " " + bin2);
		
		
	}
	
	
	

}

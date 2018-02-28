package com.ex.datatype;

public class Primatives
{
	/*
	 * int, char, long, double, boolean, float, short, byte
	 * 
	 * Integer, Character, Long, Double, Boolean, Float, Short, Byte
	 */
	
	int f;
	final int test;
	
	public Primatives()
	{
		this(10);
	}
	
	public Primatives(int f)
	{
		this.f = f;
		this.test = 1;
	}
	
	public static void main(String[] args)
	{
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2,31));
		System.out.println(Long.MAX_VALUE);
		
		int x = 1_000_000_000;
		// int wrong = _111___;
		double y = 100.0_100_0;
		// double no = 100._100;
		
		long z = 1000000000000000000L;
		
		// Autoboxing
		Integer wrap = x;
		
		// Unbox
		Integer unbox = new Integer(25);
		int a = unbox;
		
		short b = 5;
		int ex = b;
		
		int small = 10;
		b = (short)small;
		
		// octal numbers (digits 0-7)
		int oct = 017;
		System.out.println(oct);
		
		// hex (0-9, A-F) - 0x OR 0X
		int hex = 0xFF;
		int h2 = 0XA23B;
		
		// binary (0, 1) 0b OR 0B
		int bin = 0b0101011011001;
		int b2 = 0B01101001;
		
		// final int test;
		
	}
}
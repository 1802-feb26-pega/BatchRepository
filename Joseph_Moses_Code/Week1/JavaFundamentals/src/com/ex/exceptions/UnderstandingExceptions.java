package com.ex.exceptions;

public class UnderstandingExceptions {

	public static void main(String[] args) {
		
		System.out.println("at beginning of main");
		int[] arr = new int[4];
		arr[0] = 1;
		try {
			arr[10] = 10;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("sorry, that index doesn't exits");
			arr[arr.length-1] = 10;
		}
		finally {
			System.out.println("testing finally");
		}
		System.out.println("at end of main");
	}
	
}

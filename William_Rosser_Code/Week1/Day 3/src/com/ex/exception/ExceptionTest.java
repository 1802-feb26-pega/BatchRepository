package com.ex.exception;

public class ExceptionTest {

	public static void main(String[] args) {
		System.out.println("At the beginning of main.");
		int arr[] = new int[4];
		arr[0] = 1;
		try {
			arr[10] = 10;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Sorry, that index doesn't exist.");
			arr[arr.length-1] = 10;
		}
		
		System.out.println("At end of main.");
	}

}

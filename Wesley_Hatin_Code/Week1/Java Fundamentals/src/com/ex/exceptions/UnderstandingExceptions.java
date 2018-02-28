package com.ex.exceptions;

public class UnderstandingExceptions {

	public static void main(String[] args) {
		System.out.println("at beginning of main");
		int[] arr = new int[4];
		try {
			arr[0] = 1;
			arr[10] = 10;
		} catch (ArrayIndexOutOfBoundsException a) {
			a.printStackTrace();
			System.out.println("Sorry, that index doesn't exist.");
			arr[arr.length-1] = 10;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("idk");
		}
		
		finally {
			System.out.println("testing finally");
		}
		
		System.out.println("at end of main");

	}

}

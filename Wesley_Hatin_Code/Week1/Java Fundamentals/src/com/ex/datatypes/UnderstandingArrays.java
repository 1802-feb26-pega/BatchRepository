package com.ex.datatypes;

public class UnderstandingArrays {
	/*
	 * An array is a group of variables.
	 * Elements of an array must be the ame type
	 * elements are accessed by an index
	 * the first element is the 0th index
	 * find an array's size with arrayName.length*/
	
	public static void main(String[] args) {
		int[] x = {1, 2, 3, 4, 5};
		int[] nums = new int[5];
		nums[4] = 6;
		
		for(int i : x) {
			System.out.print(i + " ");
			
		}
		for(int i = 0; i < x.length; i++) {
			
		}
	}

}

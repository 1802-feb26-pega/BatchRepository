package com.ex.datatypes;

import java.util.Arrays;

public class UnderstandingArrays {
	/*
	 * An array is a group of variables
	 * Elements of an array must be the same type
	 * elements are accessed by and index
	 * the first element is at the 0th index
	 * find an array's size with arrayName.length
	 */
	
	public static void main(String[] args) {
		int [] x = {1,29,13,41,5};
		int[] nums = new int[5];
		nums[0] = 1;
		
		for(int i : x) {
			System.out.print(i + " ");
		}
		for(int i = 0; i < x.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println("\n");
		for(int i : nums) {
			System.out.println(i + " ");
		}
		
		int [][] twoD = {{2,4,6},{1,3,5}};
		int a = twoD[0][2];
		System.out.println(a);
		
		String s = " Hi my name is Phil";
		String[] spilt = s.split("");
		
		//Arrays class -- java.util.arrays
		/*
		 * There are often time when we need to do tasks on an arrays uch as
		 * fill it witha value, sort, do a binary search, ect...
		 * 
		 */
		
		System.out.println(Arrays.toString(x));
		Arrays.sort(x);
		System.out.println(Arrays.toString(x));
		
		
		
		
		
	}

}

package com.ex.datatypes;

import java.util.Arrays;

public class UnderstandingArrays {

	/*
	 * An array is a group of variables
	 * Elements of an array must be the same type
	 * elements are accessed by an index
	 * the first element is at the 0th index
	 * find an array's size with arrayName.length
	 */

	public static void main(String[] args) {

		int[] x = {10, 2, 1231, 2131, 25, 4};
		int[] nums = new int[5];
		nums[0] = 1;
		
		for(int i : x) {
			System.out.print(i + " ");
		}
		
		System.out.println("\n");
		
		for(int i: nums) {
			System.out.println(i + " ");
		}
		
		int[][] twoD = {{2, 4, 6},{1, 2, 3}};
		int a = twoD = [0][2];
		System.out.println(a);
		
		String sentence = "hi my name is Joseph";
		String[] split = sentence.split(" ");
		
		//Arrays class -- java.util.Arrays
		/*
		 * There are often times when we need to do tasks on an array such as
		 * fill it with a value, sort, do a binary search, etc
		 */
		
		System.out.println(Arrays.toString(x));
		Arrays.sort(x);
		System.out.println(Arrays.toString(x));
		
	}
}

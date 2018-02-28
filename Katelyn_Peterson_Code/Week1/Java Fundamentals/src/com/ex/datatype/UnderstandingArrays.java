package com.ex.datatype;

import java.util.Arrays;

public class UnderstandingArrays
{
	/*
	 * An array is a group of variables
	 * Elements of an array must be the same type
	 * Elements are accessed by an index
	 * The first element is at the 0th index
	 * Find an array's size with arrayName.length
	 */
	
	public static void main(String[] args) {
		int[] x = {10, 1, 3, 2, 4};
		int[] nums = new int[5];
		nums[0] = 1;
		
		for (int y: x)
		{
			System.out.println(y);
		}
		
		int[][] twoD = {{2, 4, 6}, {1, 3, 5}};
		int a = twoD[0][2];
		System.out.println(a);
		
		String sentence = "Hi my name is Tinuvial";
		String[] split = sentence.split(" ");
		
		// Arrays class -- java.util.Arrays
		/*
		 * There are often times when we need to do tasks on an array such as
		 * fill it with a value, sort, do a binary search, etc
		 */
		
		System.out.println(Arrays.toString(x));
		Arrays.sort(x);
		System.out.println(Arrays.toString(x));
		
		varargs();
		varargs(7);
		varargs(7, 3474, 124567, 345);
	}
	
	static int varargs(int... nums)
	{
		int sum = 0;
		
		for (int x: nums)
		{
			sum = sum + x;
		}
		
		return sum;
	}
}

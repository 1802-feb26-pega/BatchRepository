package com.ex.exceptions;

public class UnderstandingException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			System.out.println("at beginning of main");
		
			int[] arr = new int[4];
			arr[0]=1;
			try {
				arr[10] = 10;
			}catch(NullPointerException e) {
				e.printStackTrace();
				System.out.println("sorry, that index doesn't exist");
				arr[arr.length - 1] = 10;
			}
			finally{
				System.out.println("Testing finally");
			}
		
			System.out.println("at the end of chain");
		
		
	}

}
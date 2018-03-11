package com.ex.functional;

public class LambdaIntro {
	/* Lambda expressions were introduced to Java in version 1.8
	 * They can be used to represent functional interfaces in 
	 * a clear and concise way in the form of
	 * (arguments) -> body;
	 * 
	 */
	public static void main(String[] args) {
		
		Runnable thispagposjrihj = () -> {
			System.out.println("is running");
		};
		
	}

}
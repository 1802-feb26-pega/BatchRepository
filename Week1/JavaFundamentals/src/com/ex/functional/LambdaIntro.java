package com.ex.functional;

public class LambdaIntro {
	/*
	 * lambda expressions where introduced to java in version 1.8
	 * they can be used to represent functional interfaces in 
	 * a clear and concise way in the form of 
	 * (arguments) -> body;
	 * 
	 */
	public static void main(String[] args) {
		Runnable run = () -> {
			System.out.println("is running");
		};
	}
}

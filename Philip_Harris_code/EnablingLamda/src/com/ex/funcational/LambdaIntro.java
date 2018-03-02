package com.ex.funcational;

public class LambdaIntro {
/*
 * Lambda expressions were introduced to Java in version 1.8
 * they can be used to represent functional interfaces in
 * a clear and concise way in the form of
 * (arugments) -> (body);
 */
	public static void main(String[] args) {
		Runnable run = () -> {
			System.out.println("is Run");
		};
	}
	
	
	
}

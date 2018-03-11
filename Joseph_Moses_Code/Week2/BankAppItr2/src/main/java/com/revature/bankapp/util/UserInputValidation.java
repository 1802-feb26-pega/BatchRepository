package com.revature.bankapp.util;

import java.util.Scanner;

public class UserInputValidation {

	public static String validateInput(int type) {
		
		Scanner scan = new Scanner(System.in);
		
		String input;
		boolean loopFlag = false;
		
		do {
			input = scan.next();
			switch(type) {
			case 1:	if(!validateNumberOnlyInput(input)) {
						System.out.println("You have entered an invalid selection.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 2:	if(!validateEmailInput(input)) {
						System.out.println("You have entered an invalid email.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 3:	if(!validateCurrencyInput(input)) {
						System.out.println("You have entered an invalid amount.");
						System.out.println("Please reinput the amount:");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 4: if(!validateNameInput(input)) {
						System.out.println("You have entered an invalid character.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 5: if(!validateAccountIdInput(input)) {
						System.out.println("Youn have entered an invalid account Number.");
						loopFlag = true;	
					} else {
						loopFlag = false;
					}
			}	
		} while(loopFlag);
		
		//scan.close();
		return input;
	}
	
	private static boolean validateNumberOnlyInput(String input) {
		
		if(input.matches("[^0-9]{1}")) {
			return false;
		}
		
		return true;
	}
	
	private static boolean validateCurrencyInput(String input) {
		
		if(input.matches("[+]?\\d*\\.?\\d{2}") && Double.parseDouble(input) < Double.MAX_VALUE) {
			return true;
		}
		else return false;
	}
	
	private static boolean validateEmailInput(String input) {
		
		if(input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		
		return false;
	}
	
	private static boolean validateNameInput(String input) {
		if(input.matches("[a-zA-Z]+")) {
			return true;
		}
		
		return false;
	}
	
	private static boolean validateAccountIdInput(String input) {
		
		if(input.matches("[^0-9]+")) {
			return false;
		}
		
		return true;
	}
	
}

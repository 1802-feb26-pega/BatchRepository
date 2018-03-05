package com.bank.applications;

import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Teller t = new Teller();
		int option;
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to your automated bank terminal!");
		System.out.println("If you would like to log in to your account, please press '1'.");
		System.out.println("If you would like to create a new account, please press '2'.");
		option = scan.nextInt();
		System.out.println("");
		
		switch(option) {
			case 1:
				t.logIn();
				break;
			case 2:
				t.createAccount();
				break;
		}
		
		while(t.getUsername()!=null) {
			
		}
		scan.close();
	}

}

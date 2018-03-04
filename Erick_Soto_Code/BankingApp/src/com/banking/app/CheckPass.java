package com.banking.app;

import java.util.HashMap;
import java.util.Scanner;

public class CheckPass {

	public void CheckPassMethod(){
		String un;
		String pw;
		HashMap<String, User> map = null; 
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your username");
		un = keyboard.nextLine();
		System.out.println("Enter password");
		pw = keyboard.nextLine();
		
		CreateAccount getmap = new CreateAccount();
		getmap.HashmapDeSerialization();
		map = getmap.unserialized;
		
		if(pw.equals(map.get(un).getPassWord())) {
			System.out.println("success");
			OptionsMenu optionsmenu = new OptionsMenu();
			optionsmenu.OptionsMenuMethod(map, un);
		}
		else {
			System.out.println("wrong password");
			WelcomeMenu welcomemenu = new WelcomeMenu();
			welcomemenu.WelcomeMenuMethod();
		}
		
	}
}

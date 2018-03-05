package com.run;

import java.util.Scanner;

import com.UI.UserApp;
import com.io.UserIO;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello! Welcome to the greatest bank in the world!\n");
		System.out.println("Our new online site is still in development, "
				+ "but please feel free to use it.\n");
		
		// Populate the hash set in the UserIO class with all created users
		UserIO.readFile();
		
		UserApp userApp = new UserApp();
		userApp.userOptions();
		UserIO.writeFile();
	}
	
}

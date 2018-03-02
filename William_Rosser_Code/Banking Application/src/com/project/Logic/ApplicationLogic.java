package com.project.Logic;

import com.project.UI.ApplicationUI;

public class ApplicationLogic {
	
	private static AppMemory app;
	
	public static void main(String[] args) {
		ApplicationUI aui = new ApplicationUI();
		app = new AppMemory();
		//check for serialized user from previous session.
		//if user is found, prompt for password.
		//if password is correct, log in. If password is not, prompt error. If password is null, ignore recovered user account.
		if (user == null) {
			//Print Welcome statement.
			//Ask if user wants to register or log in.
		}
		
		//Main program loop.
		boolean quit = false;
		while (!quit) {
			//Ask if user wants to:
				//Deposit Money
				//View Balance
				//Withdraw money
				//Log out
			int choice = 0;
			if (choice == 1) {
				//Deposit
				float deposit = aui.depositMoney(user.getWholeName(), user.getBalance());
			} else if (choice == 2) {
				// View Balance
			} else if (choice == 3) {
				//Withdraw Money
			} else if (choice == 4) {
				quit = true;
				//Begin serialization.
			}
		}
	}

}

package com.banking.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeMenu {
	
	private int decision;
	
	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}


	public void WelcomeMenuMethod(){
		
		boolean wrongInput = true;
		
		do {
			try {
				System.out.println("To login press 1");
				System.out.println("To create an account press 2");
			
				Scanner keyboard = new Scanner(System.in);
				setDecision(keyboard.nextInt());
				keyboard.nextLine();
				
				if(getDecision() != 1 && getDecision() != 2)
					throw new InputMismatchException();
				
				wrongInput = false;
			}
			catch(InputMismatchException a){
				System.out.println("invalid input");
			}
			
			//System.out.println(getDecision());
		}while(wrongInput);
		
		
		
		if(decision == 1) {
			CheckPass checkpass = new CheckPass();
			checkpass.CheckPassMethod();
		}
		else if(decision == 2) {
			CreateAccount accountCreation = new CreateAccount();
			accountCreation.CreateAccountMethod();
		}
	}
}

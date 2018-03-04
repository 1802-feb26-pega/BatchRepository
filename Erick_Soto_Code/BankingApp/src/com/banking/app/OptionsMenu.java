package com.banking.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionsMenu {
	
	private int decision;
	
	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}
	
	public void OptionsMenuMethod(HashMap<String, User> map, String un) {
		boolean wrongInput = true;
		boolean keepgoing = true;
		Scanner keyboard = new Scanner(System.in);
				do {
					do {
						try {
							System.out.println("To deposit money press 1");
							System.out.println("To withdraw money 2");
							System.out.println("To view balance press 3");
							System.out.println("To log out press 4");
						
							//Scanner keyboard = new Scanner(System.in);
							setDecision(keyboard.nextInt());
							keyboard.nextLine();
							
							if(getDecision() != 1 && getDecision() != 2 && getDecision() != 3 && getDecision() != 4 )
								throw new InputMismatchException();
							
							wrongInput = false;
						}
						catch(InputMismatchException a){
							System.out.println("invalid input");
						}
						
						System.out.println(getDecision());
					}while(wrongInput);
					
					if(decision == 1) {
						
						System.out.println("Enter how much you want to deposit ");
						double deposit = keyboard.nextDouble();
						double total = deposit + map.get(un).getBalance();
						map.get(un).setBalance(total);
						System.out.println("Your new total is " + map.get(un).getBalance());
						
					}else if(decision == 2) {
						
						System.out.println("How much would you like to withdraw? ");
						double withdrawal = keyboard.nextDouble();
						double total = map.get(un).getBalance() - withdrawal;
						map.get(un).setBalance(total);
						System.out.println("Your new total is " + map.get(un).getBalance());
						
					}else if(decision == 3) {
						
						System.out.println("Balance is " + map.get(un).getBalance() + "\n");
						
					}else if(decision == 4) {
						keepgoing = false;
						System.out.println("logged out");
					}
				}while(keepgoing);
				HashmapSerializationB(map);
				WelcomeMenu welcomeMenu = new WelcomeMenu();
				welcomeMenu.WelcomeMenuMethod();
	}
	
	public void HashmapSerializationB(HashMap<String, User> map) {
		try {
			FileOutputStream fos = new FileOutputStream("src/hashMap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.close();
			fos.close();
			//System.out.println("Account saved to database");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

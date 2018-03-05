package com.ex.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 
Create a mock bank using solely Java 1.8. Persist your data in a text file. 
You may also write functionality to serialize your bank after every transaction but 
this is not a requirement.

As a user, I can:
* create an account with a unique email or username
* log in 
* log out
* deposit money
* withdraw money
* view balance

To note: There are no strict requirements regarding the flow of operation for your program, but keep the user in mind. Is your menu clear? Is user input validated? Do I have to log back in after each transaction? All important things to consider. 

Good luck!
 */

public class bankApp {
	
	// Map used to store everything in the text file
	public static Map<String, ArrayList<String>> updateMap() {
		final String file = "src/com/ex/data/file.txt";
		
		// map used to store username as key and the array list to store multiple things
		// such as password and balance
		Map<String, ArrayList<String>> accounts = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> fileLine = new ArrayList<String>();
		
		try(BufferedReader br = Files.newBufferedReader(Paths.get(file))){
			String line = null;
			while((line=br.readLine()) != null) {
				fileLine.add(line.substring(line.indexOf(':'+1), line.indexOf(','))); // password
				fileLine.add(line.substring(line.indexOf(','+1))); // balance
				
				accounts.put(line.substring(0, line.indexOf(':')), fileLine); // use account name as key
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	public static void main(String[] args) {
		
		char block = '0';
		boolean clear = false;
		boolean done = false;
		final String file = "src/com/ex/data/file.txt";
		
		do {
			switch (block) {
			case '0': // BEFORE LOGGING iN AND/OR CREATING AN ACCOUNT
				do {
					System.out.println("What would you like to do?");
					System.out.println("To create an account press '1'");
					System.out.println("To login press '2'");

					Scanner input0 = new Scanner(System.in);
					int inputInt = input0.nextInt();

					if (inputInt == 1) {
						block = '1';
						clear = true;
					} else if (inputInt == 2) {
						block = '2';
						clear = true;
					} else {
						System.out.println("Please select a valid option:");
					}
				} while (clear == false);
			case '1': // CREATE AN ACCOUNT BLOCK
				clear = false;
				do {

					System.out.println("Please enter a unique username:");
					Scanner input1 = new Scanner(System.in);
					String userName = input1.nextLine();
					List<String> lines = new ArrayList<String>();
					
					boolean empt = false;
					
					try(BufferedReader br = new BufferedReader(new FileReader(file))){
						String line = null;

						if (br.readLine() == null) {
							empt = true;
						} else {
							empt = false;
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (empt == true) {
						try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
							bw.write(userName);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try(BufferedReader br = Files.newBufferedReader(Paths.get(file))){
							String line = null;
							while((line=br.readLine()) != null) {
								lines = br.lines().collect(Collectors.toList());
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						for (int i = 0; i < lines.size(); i++) {
							if (userName.equals(lines.get(i).substring(0, ':'))) {
								System.out.println("Username already exists enter a unique username or login:");
								break;
							} else {
								try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
									bw.newLine();
									bw.write(userName);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						}
					}
					System.out.println("User " + userName + " created:");
					
					Boolean match = false;
					do {
						System.out.println("Please enter a password:");
						Scanner paw1 = new Scanner(System.in);
						String pw1 = paw1.nextLine();

						System.out.println("Please re-enter the password:");
						Scanner paw2 = new Scanner(System.in);
						String pw2 = paw2.nextLine();

						if (pw1.equals(pw2)) {
							try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
								bw.write(":"+pw1+",0"); // the colon is used for parsing
								// also add ,0 : 0 is initial balance of user comma is for parsing
							} catch (IOException e) {
								e.printStackTrace();
							}
							match = true;
							System.out.println("Passwords match!");
							System.out.println("Account created!");
							block = '0';
							break;
						} else {
							System.out.println("Passwords do NOT match please try again: ");
						}
					} while (match == false);

				} while (clear == false);
				break;
				
			case '2': { // ACCOUNT/LOGIN BLOCK
				System.out.println("Please enter your username: ");
				Scanner usrn1 = new Scanner(System.in);
				String un1 = usrn1.nextLine();
				
			}
			
			
			// end of switch block
			} 
		} while (done == false);
		
	}

}

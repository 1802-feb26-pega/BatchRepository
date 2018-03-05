package com.ex.code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class bankApp2 {
	
	// Map used to store everything from the text file
	public static Map<String, ArrayList<String>> updateMap() {
		final String file = "src/com/ex/data/file.txt";

		// map used to store username as key and the array list to store multiple things
		// such as password and balance
		Map<String, ArrayList<String>> accounts = new HashMap<String, ArrayList<String>>();

		ArrayList<String> fileLine = new ArrayList<String>();

		try(BufferedReader br = Files.newBufferedReader(Paths.get(file))){
			String line = null;
			while((line=br.readLine()) != null) {
				fileLine.add(line.substring(line.indexOf(','+1), line.indexOf(';'))); // password
				fileLine.add(line.substring(line.indexOf(';'+1))); // balance

				accounts.put(line.substring(0, line.indexOf(':')), fileLine); // use account name as key
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return accounts;
	}
	
	// helper function to write stuff
	public static void writeToFile(File file, String string, boolean newLine) {
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
        	if (newLine == true) {
        		bw.newLine();
        	}
			bw.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// helper function to read file and get each individual line in a list
	public static ArrayList<String> readLines(File file) {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while((line=br.readLine()) != null) {
				line = br.readLine();
				System.out.println("line read is "+line);
				arr.add(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public static boolean emp(File file) {
		boolean empt = false;
		
		if (file.length() == 0) {
			System.out.println("empty");
			empt = true;
		} else {
			empt = false;
			System.out.println("NOT empty");
		}
		
		return empt;
	}
	
	public static void main(String[] args) {

		File file = new File("src/com/ex/data/file.txt");
		char block = '0';
		boolean clear = false;
		boolean done = false;
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

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
				break;
			case '1': // CREATE AN ACCOUNT BLOCK
				clear = false;
				
				do {
					boolean empt = false;
					
					if (file.length() == 0) {
						System.out.println("empty");
						empt = true;
					} else {
						empt = false;
						System.out.println("NOT empty");
					}
					
					boolean unique = false;
					
					do {
						System.out.println("Please enter a unique username:");
						Scanner input1 = new Scanner(System.in);
						String userName = input1.nextLine();
						if (empt) {
							writeToFile(file,":"+userName,false);
							unique = true;
						} else {
							System.out.println(readLines(file));
							if(readLines(file).contains(":"+userName)) {
								System.out.println("Username already taken, please enter a different username:");
							} else {
								writeToFile(file, ":"+userName, true);
								unique = true;
							}
						}
					} while (unique == false);
					
					boolean match = false;
					do {
						System.out.println("Please enter a password:");
						Scanner pass1 = new Scanner(System.in);
						String pw1 = pass1.nextLine();
						System.out.println("Please re-enter the password:");
						Scanner pass2 = new Scanner(System.in);
						String pw2 = pass2.nextLine();
						
						if (pw1.equals(pw2)) {
							System.out.println("Passwords match!");
							System.out.println("Account created!");
							
							writeToFile(file,","+pw2+";",false);
							writeToFile(file,"0",false); // set account balance to zero
							match = true;
							clear = true;
							break;
						} else {
							System.out.println("Passwords do not match please try again:");
						}
						
					} while (match = false);
					
				} while (clear = false);
				block = 0;	
				break;

			case '2': { // ACCOUNT/LOGIN BLOCK
				if (!emp(file)) {
					map = updateMap();
				} else {
					System.out.println("No accounts created yet");
					block = 0;
					break;
				}
				
				boolean good = false;
				String un1;
				do {
					System.out.println("Please enter your username: ");
					Scanner usrn1 = new Scanner(System.in);
					un1 = usrn1.nextLine();

					if (map.containsKey(un1)) {
						good = true;
					} else {
						System.out.println("Invalid username try again");
					}
				} while (good = false);
				
				boolean correct = false;
				String user;
				do {
					System.out.println("Please enter your password");
					Scanner pw2 = new Scanner(System.in);
					String pw22 = pw2.nextLine();
					
					if (map.containsKey(map.get(un1).get(0))) {
						System.out.println("Sucessfully logged in!");
						user = un1;
						correct = true;
					} else {
						System.out.println("Incorrect password");
					}
					
				} while (correct == false);
				
				boolean notdone = false;
				String cho;
				
				do {
					System.out.println("What would you like to do?");
					System.out.println("Deposit      '1'");
					System.out.println("Withdraw     '2'");
					System.out.println("View Balance '3'");
					System.out.println("Log out      '4'");
					
					Scanner input22 = new Scanner(System.in);
					cho = input22.nextLine();
					
					if (!cho.equals("1") || !cho.equals("1") || !cho.equals("1")) {
						System.out.println("Please choose a valid option");
					} else {
						notdone = true;
					}
					
				} while (notdone = false);
				
				boolean fin = false;
				do {
					switch (cho) {
					case "1":
						System.out.println("How much would you like to deposit?");
						Scanner depo = new Scanner(System.in);

						int dep = depo.nextInt();
						add(Integer.parseInt(map.get(un1)).get(1) + dep);


						break;
					case "2":
						System.out.println("How much would you like to withdraw?");
						Scanner with = new Scanner(System.in);

						int wd = with.nextInt();
						if (wd > Integer.parseInt(map.get(un1).get(1))) {
							System.out.println("Insufficient funds, transaction cancelledd");
						}


						break;
					case "3":
						System.out.println("Your balance is: "+map.get(un1).get(0));
						break;
					case "4":
						System.out.println("Logged out");
						fin = true;
						break;

					}
				} while(fin = false);
				
			}


			// end of switch block
			} 
		} while (done == false);

	}

}

package com.ex.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BankAppBetter {
	// "c:/users/Marvin/Documents/github/BankingApplication/src/com/ex/date/file"
	
	// File will have user, password, and balance all on same line, new users will be separate lines
	final static Path file = Paths.get("src/com/ex/data/file.txt"); // for Path methods (createFile)
	final static String filename = "src/com/ex/data/file.txt"; // for String methods (buffered reader/writer)
	final static File fileF = new File("src/com/ex/data/file.txt"); // for File methods (check if file is empty)
	
	
	// METHOD TO WRITE TO FILE
	public static void writeToFile(String string) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			bw.write(string);
			bw.newLine(); // write new line to get ready for next input
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// METHOD TO UPDATE USER INFORMATION
	public static void updateUserInfo(Map<String, ArrayList<String>> map) {
		
		// clear file
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.close();
		
		// re-write it
		Set<String> keys = map.keySet();
		Object[] kar = keys.toArray();
		for(int i = 0; i < keys.size(); i++) {
			writeToFile(":"+kar[i]+","+map.get(kar[i]).get(0)+";"+map.get(kar[i]).get(1));
		}
		
	}
	
	// METHOD TO READ FROM FILE AND RETURN AS A HASHMAP
	public static Map<String, ArrayList<String>> readFromFile() {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> arr = new ArrayList<String>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = null;
			
			while((line=br.readLine()) != null) {
				
				int afterComma = line.indexOf(',') + 1;
				arr.add(line.substring(afterComma, line.indexOf(';'))); // password
				int afterSemi = line.indexOf(';') + 1;
				arr.add(line.substring(afterSemi)); // balance
				
				//int afterColon = line.indexOf(':')+1;
				map.put(line.substring(1, line.indexOf(',')), arr); // use account name as key
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	// MAIN MENU BLOCK
	public static void mainMenu() {
		System.out.println("What would you like to do?");
		System.out.println("To create an account press '1'");
		System.out.println("To login press '2'");

		Scanner input0 = new Scanner(System.in);
		int inputInt = input0.nextInt();
		
		if (inputInt == 1) {
			createUser();
		} else if (inputInt == 2) {
			login();
		} else {
			System.out.println("Please enter a valid option:");
			mainMenu();
		}
	}
	
	// LOGIN BLOCK
	public static void login() {
		System.out.println("Please enter your username:");
		Scanner input1 = new Scanner(System.in);
		String userName = input1.nextLine();
		
		Map<String, ArrayList<String>> accounts = readFromFile(); // get map of user accounts from text file
		
		if(accounts.containsKey(userName) == false) {
			System.out.println("Username does not exist");
			System.out.println("Press '9' to create an account or any other key to try again");
			
			Scanner input2 = new Scanner(System.in);
			String ip2 = input2.nextLine();
			
			if (ip2.charAt(0) == '9') {
				createUser();
			} else {
				login();
			}
		} 
		
		boolean pass = false;
		
		do {
			System.out.println("Please enter your password");
			Scanner input2 = new Scanner(System.in);
			String pw = input1.nextLine();

			if(( accounts.get(userName).get(0) ).equals(pw) == false) {
				System.out.println("Password incorrect");
				System.out.println("Log in as a different user? '9'");
				System.out.println("Or try again by pressing any key");

				Scanner input3 = new Scanner(System.in);
				String ip3 = input2.nextLine();

				if (ip3.charAt(0) != '9') {
					login();
				} else {
					pass = false;
				}
			} else {
				System.out.println("Login successful!");
				pass = true;
				userOptions(userName);
			}
		} while (pass = false);
		
	}
	
	// CREATE USER BLOCK
	public static void createUser() {
		System.out.println("Please enter a unique username:");
		Scanner input1 = new Scanner(System.in);
		String userName = input1.nextLine();
		
		Map<String, ArrayList<String>> accounts = readFromFile();
		System.out.println("does it exist? "+accounts.containsKey(userName));
		if (accounts.containsKey(userName) == true) {
			System.out.println("Username already exists");
			System.out.println("Press '9' to login or any other key to enter a unque user name");
			Scanner input2 = new Scanner(System.in);
			String ip2 = input2.nextLine();
			
			if (ip2.charAt(0) != '9') {
				createUser();
			} else {
				mainMenu();
			}
		}
		
		System.out.println("Please enter a password:");
		Scanner pass1 = new Scanner(System.in);
		String pw1 = pass1.nextLine();
		System.out.println("Please re-enter the password:");
		Scanner pass2 = new Scanner(System.in);
		String pw2 = pass2.nextLine();
		
		if (pw1.equals(pw2)) {
			System.out.println("Passwords match!");
			if (fileF.length() == 0) { // if file is empty
				writeToFile(":"+userName+","+pw1+";0"); // set balance to zero ':', ';', and ',' are for parsing
			}
			System.out.println("Account created!");
			mainMenu();
		} else {
			System.out.println("Passwords do NOT match!");
			System.out.println("Try again:");
			createUser();
		}
		
		
		
	}
	
	// USER OPTIONS BLOCK
	public static void userOptions(String userName) {
		System.out.println("What would you like to do?");
		System.out.println("View Balance      '1'");
		System.out.println("Make a Deposit    '2'");
		System.out.println("Make a Withdrawal '3'");
		System.out.println("Logout            '4'");
		
		Scanner input = new Scanner(System.in);
		String ip1 = input.nextLine();
		
		Map<String, ArrayList<String>> accounts = readFromFile();
		
		if (ip1.equals("1")) {
			System.out.println("Your balance is "+accounts.get(userName).get(1));
			userOptions(userName);
		} else if (ip1.equals("2")) {
			makeDeposit(userName);
		} else if (ip1.equals("3")) {
			makeWithdrawal(userName);
		} else if (ip1.equals("4")) {
			System.out.println("Successfully logged out!");
			mainMenu();
		} else {
			System.out.println("Please enter a valid option:");
			userOptions(userName);
		}
		
	}
	
	// DEPOSIT BLOCK
	public static void makeDeposit (String userName) {
		Map<String, ArrayList<String>> accounts = readFromFile();
		
		System.out.println("How much do you want to deposit?");
		Scanner depo = new Scanner(System.in);
		Double dep = 0.0;
		
		if (depo.hasNextDouble()) {
			dep = depo.nextDouble();
		} else {
			System.out.println("Please enter a valid amount:");
			makeDeposit(userName);
		}
		
		Double curr = Double.parseDouble(accounts.get(userName).get(1));
		curr = curr + dep;
		String toAdd = Double.toString(curr);
		accounts.get(userName).set(1, toAdd);
		
		System.out.println("Deposit successful!");
		System.out.println("New total is "+accounts.get(userName).get(1));
		
		updateUserInfo(accounts);
		userOptions(userName);
	}
	
	// WITHDRAW BLOCK
	public static void makeWithdrawal (String userName) {
		Map<String, ArrayList<String>> accounts = readFromFile();
		
		System.out.println("Your current balance is "+accounts.get(userName).get(1));
		System.out.println("How much do you want to withdraw?");
		Scanner amount = new Scanner(System.in);
		double wd = 0;
		
		if (amount.hasNextInt()) {
			wd = amount.nextDouble();
		} else {
			System.out.println("Please enter a correct value:");
			makeWithdrawal(userName);
		}
		
		if (wd > Double.parseDouble(accounts.get(userName).get(1))) {
			System.out.println("Insufficient funds, cannot complete transaction: ");
			makeWithdrawal(userName);
		} else {
			
			Double curr = Double.parseDouble(accounts.get(userName).get(1));
			curr = curr - wd;
			String toAdd = Double.toString(curr);
			accounts.get(userName).set(1, toAdd);
			
			System.out.println("Withdrawal successful!");
			System.out.println("New total is "+accounts.get(userName).get(1));
			updateUserInfo(accounts);
			userOptions(userName);
		}
		
	}
	
	public static void main(String[] args) {
		
		// create file to store things if not already exists
		/*try {
			Files.createFile(file);
		} catch (FileAlreadyExistsException faee) {
			// do nothing file already exists
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		mainMenu();
		
	}

}

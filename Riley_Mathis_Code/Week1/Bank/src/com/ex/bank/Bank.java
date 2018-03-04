package com.ex.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bank {
	//static Set<String> users = new HashSet<>();
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		welcome(scan);
		
		scan.close();
	}
	public static void welcome(Scanner scan) {
		String temp = "";
		System.out.println("Welcome\nRegister(r)--Login(l)");
		temp = scan.nextLine();
		if(temp.equals("r")) {
			register(scan);
		}
		else if(temp.equals("l")) {
			login(scan);
		}
		else {
			System.out.println("Invalid input! Must be 'r' or 'l'.");
		}
	}
	
	public static void register(Scanner scan) {
		String fn,ln,un,pw = "";
		System.out.println("Register");
		System.out.println("Firstname:");
		fn = scan.nextLine();
		System.out.println("Lastname:");
		ln = scan.nextLine();
		System.out.println("Username:");
		un = scan.nextLine();
		System.out.println("Password:");
		pw = scan.nextLine();
		
		User user = new User(fn,ln,un,pw);
		File fileName = new File(user.getFileName());
		
		if(fileName.exists()) {
			System.out.println("Username already exists!");
			register(scan);
		}
		else {
			String output = fn+":"+ln+":"+un+":"+pw+":"+user.getBalance();
			//users.add(un);
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(user.getFileName(), false))){
				bw.write(output.toString());
			} catch(IOException e) {
				e.printStackTrace();
			}
			login(scan);
		}
	}
	
	public static void login(Scanner scan) {
		String un,pw,checkPw = "";
		List<String> data = new ArrayList<>();
		System.out.println("Login");
		System.out.println("Username:");
		un = scan.nextLine();
		File fileName = new File("src/data/"+un+".txt");
		System.out.println(un + "  " + fileName);
		
		if(fileName.exists()) {
			try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
				String line = br.readLine();
				data = Arrays.asList(line.split(":"));
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			checkPw = data.get(3);
		}
		else {
			System.out.println("That username doesn't exist!");
			login(scan);
		}
		System.out.println("Password:");
		pw = scan.nextLine();
		
		if(pw.equals(checkPw)) {
			atm(scan, un);
		}
		else {
			System.out.println("Incorrect password for Username: " + un);
			login(scan);
		}
		
	}
	
	public static void atm(Scanner scan, String username) {
		String choice = "";
		System.out.println("Deposit(d) -- Withdraw(w) -- Show Balance(b) -- Logout(l)");
		choice = scan.nextLine();
		if(choice.equals("d")) {
			deposit(scan, username);
		}
		else if (choice.equals("w")) {
			withdraw(scan, username);
		}
		else if (choice.equals("b")) {
			showBalance(scan, username);
		}
		else if (choice.equals("l")) {
			welcome(scan);
		}
		else {
			System.out.println("Invalid input! Must be 'd', 'w', or 'b'.");
			atm(scan, username);
		}
	}
	
	public static void deposit(Scanner scan, String username) {
		
		String fileName = "src/data/"+username+".txt";
		String output = "";
		String currentBalance = "";
		List<String> data = new ArrayList<>();
		int amount = 0; 
		Integer newBalance = 0;
		
		
		System.out.println("How much to deposit?");
		try {
			amount = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan,username);
		}
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line = br.readLine();
			data = Arrays.asList(line.split(":"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(amount > 0) {
			currentBalance = data.get(4);
			newBalance = Integer.parseInt(currentBalance) + amount;
		
			data.set(4,newBalance.toString());
			for(int i = 0; i<5; i++) {
				if(i==4) {
					output = output.concat(data.get(i));
				}
				else {
					output = output.concat(data.get(i)+":");
				}
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))){
				bw.write(output.toString());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			showBalance(scan, username);
			atm(scan, username);
		}
		else {
			System.out.println("Input a valid amount!");
			atm(scan, username);
		}
	}
	
	public static void withdraw(Scanner scan, String username) {
		String fileName = "src/data/"+username+".txt";
		String output = "";
		String currentBalance = "";
		List<String> data = new ArrayList<>();
		int amount = 0; 
		Integer newBalance = 0;
		
		
		System.out.println("How much to withdraw?");
		
		try {
			amount = scan.nextInt();
			scan.nextLine();
		} catch(InputMismatchException ime) {
			System.out.println("Invalid input!");
			atm(scan,username);
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line = br.readLine();
			data = Arrays.asList(line.split(":"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		currentBalance = data.get(4);
		newBalance = Integer.parseInt(currentBalance) - amount;
		
		if(newBalance >= 0) {
			data.set(4,newBalance.toString());
			for(int i = 0; i<5; i++) {
				if(i==4) {
					output = output.concat(data.get(i));
				}
				else {
					output = output.concat(data.get(i)+":");
				}
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))){
				bw.write(output.toString());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			showBalance(scan, username);
			atm(scan, username);
		}
		else {
			System.out.println("Input a valid amount!");
			atm(scan, username);
		}
	}
	public static void showBalance(Scanner scan, String username) {
		
		List<String> data = new ArrayList<>();
		String fileName = "src/data/"+username+".txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line = br.readLine();
			data = Arrays.asList(line.split(":"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Balance: "+data.get(4).toString());
		atm(scan, username);
	}
	
}

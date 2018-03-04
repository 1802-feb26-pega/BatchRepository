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
import java.util.List;
import java.util.Scanner;

public class Bank {
	//static Set<String> users = new HashSet<>();
	
	public static void main(String[] args) {
		String temp = "";
		Scanner scan = new Scanner(System.in);
		
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
		
		scan.close();
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
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(user.getFileName(), true))){
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
		File fileName = new File("/src/data/"+un+".txt");
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
			atm(scan);
		}
		else {
			System.out.println("Incorrect password for Username: " + un);
			login(scan);
		}
		
	}
	public static void atm(Scanner scan) {
		
	}
}

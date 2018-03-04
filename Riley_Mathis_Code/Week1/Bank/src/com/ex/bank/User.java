package com.ex.bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
	
	public static void main(String[] args) {
		
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
//			bw.write(user.toString());
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public String firstName;
	public String lastName;
	public String username;
	private String password;
	private int balance;
	
	private String file;
	
	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.balance = 0;
		this.file = "src/data/"+username+".txt";
	}
	
//	public void login() {
//		
//	}
//	
//	public void register() {
//		String temp = "";
//		String user = "";
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.println("Firstname:");
//		temp = scan.nextLine();
//		setFirstName(temp);
//		user = user.concat(getFirstName() + ":");
//		
//		System.out.println("Lastname:");
//		temp = scan.nextLine();
//		setLastName(temp);
//		user = user.concat(getLastName() + ":");
//		
//		System.out.println("Username:");
//		temp = scan.nextLine();
//		setUsername(temp);
//		user = user.concat(getUsername() + ":");
//		
//		System.out.println("Password:");
//		temp = scan.nextLine();
//		setPassword(temp);
//		user = user.concat(getPassword() + ":" + getBalance());
//		
//		scan.close();
//		
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
//			bw.write(user.toString());
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
	public void logout() {
		
	}
	
	public void withdraw() {
		
	}
	
	public void deposit() {
		
	}
	
	public String getFileName() {
		return file;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

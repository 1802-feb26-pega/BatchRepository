package com.banking.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class CreateAccount {
	
	User user = new User();
	HashMap<String,User> map = new<String, User> HashMap();
	HashMap<String,User> unserialized = null;
	
	
	public void CreateAccountMethod(){
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter a user name: ");
		user.setUserName(keyboard.nextLine());
		
		System.out.println("Enter a password: ");
		user.setPassWord(keyboard.nextLine());
		
		System.out.println("Enter your first name: ");
		user.setFirstName(keyboard.nextLine());
		
		System.out.println("Enter your last name: ");
		user.setLastName(keyboard.nextLine());
		
		user.setBalance(0);
		
		if(!CheckIfFileEmpty()) {
			HashmapDeSerialization();
			unserialized.put(user.getUserName(), user);
			map = unserialized;
			HashmapSerialization();
		}
		else {
			map.put(user.getUserName(), user);
			HashmapSerialization();
		}
		//keyboard.close();     testing put back in if doesnt work!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		WelcomeMenu welcomeMenu = new WelcomeMenu();
		welcomeMenu.WelcomeMenuMethod();
	
	}
	
	public boolean CheckIfFileEmpty() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/hashMap.ser"));     
			if (br.readLine() == null) {
				br.close();
				return true;
			} else {
				br.close();
				return false;
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return true;
	}
	
	public void HashmapSerialization() {
		try {
			FileOutputStream fos = new FileOutputStream("src/hashMap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.close();
			fos.close();
			System.out.println("Account saved to database");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void HashmapDeSerialization() {
		try {
			FileInputStream fis = new FileInputStream("src/hashMap.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			unserialized = (HashMap) ois.readObject();
			ois.close();
			fis.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
}

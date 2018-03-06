package com.project.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.project.Logic.User;

public class Archiver {
	/*
	 * This class stores the data of many users in a single text database.
	 */
	
	private static final String filename = "src/data/userArchive.txt";
	
	/**
	 * Stores the list of students to the file bin/data/userArchive.txt,
	 * overwriting it.
	 * 
	 * @param users The list of users to save.
	 */
	public static void writeUsers(ArrayList<User> users) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
			String output = "";
			for (User u: users) {
				output += u.toString();
			}
			bw.write(output);
		} catch (IOException e) {
			System.err.println("Could not open " + filename);
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates an ArrayList of instantiated Users from file. Includes all user data.
	 * @return An ArrayList of users.
	 */
	public static ArrayList<User> readUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while ((line=br.readLine()) != null) {
				String[] uData = line.split("::");
				User temp = User.instantiateUser(uData);
				users.add(temp);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find " + filename);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Could not open " + filename);
			e.printStackTrace();
		}
		return users;
	}
}

package com.bank.applications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {		
	final static String file = "src/data/clients.txt";
	
	//adds a new client entry to the data file
	public void writeNewClient(String username, String first, String last, String password) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			bw.write(username+":"+first+":"+last+":"+password+":0 ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//checks a username against all usernames in the data file, returns true if contained in the file
	public boolean usernameValidation(String givenUser){
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while((line=br.readLine()) != null) {
				String[] entry = line.split(":");
				if(String.valueOf(entry[0]).equals(String.valueOf(givenUser))) {
					return true;
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//returns a String array containing the entry that corresponds with the given username
	public String[] retrieveClientEntry(String givenUser) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while((line=br.readLine()) != null) {
				String[] entry = line.split(":");
				if(String.valueOf(entry[0]).equals(givenUser)) {
					return entry;
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//edits the balance of the entry with the given username to equal the new balance
	public void writeBalance(String username, double newBal) {
		List<String> newLines = new ArrayList<>();
		try {
			for (String entry : Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8)) {
				String[] values = entry.split(":");
				if (username.equals(String.valueOf(values[0]))) {
			    	values[4]=String.valueOf(newBal);
			    	newLines.add(values[0]+":"+values[1]+":"+values[2]+":"+values[3]+":"+values[4]);
			    } else {
			    	newLines.add(entry);
			    }
			}
		
			Files.write(Paths.get(file), newLines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.bank.applications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {		
	final static String file = "src/data/clients.txt";
	
	public void writeNewClient(String username, String first, String last, String password) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			bw.write(username+":"+first+":"+last+":"+password+":0");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
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
}

package com.revature.projectI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPersistency {
	static final String file = "src/database/Accounts.txt";
	

	public void writeCustomer(Client Customer,Account a) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			bw.write(Customer.toString() + "," + a.getBalance() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public boolean readCustomer(Client customer,Account account,String username, String password){
		
		String[] textData = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
				String line = null;
				
				while((line = br.readLine()) != null) {
						textData = line.split(",");											
						if(username.equals(textData[3]) | username.equals((textData[4]))) {
							customer.setfName(textData[0]);
							customer.setlName(textData[1]);
							customer.setSsn(Integer.parseInt(textData[2]));
							customer.setUsrName(textData[3]);
							customer.setPassword(textData[4]);
							account.setBalance(Integer.parseInt(textData[5]));
							return true;
						}	
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}


public void updateBalance(Account a,Client c) {

	String[] textData = null;
	
	try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			
			while((line = br.readLine()) != null) {
					textData = line.split(",");											
					if(c.getfName().equals(textData[0])) {		//Unique ID	
						
						textData[5] = (Integer.toString(a.getBalance()));
						
						String nString = "";
						
						for(String x: textData) {
							nString += x + ",";
						}						
						
						try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
							bw.write(nString. + "\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
			}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}





}

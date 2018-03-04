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
		
		System.out.println("");
		System.out.println("");
		System.out.println("");

	}
	public boolean readCustomer(Client customer,Account account,String username, String password){

		String[] textData = null;

		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;

			while((line = br.readLine()) != null) {
				textData = line.split(",");			
				if(username.equals(textData[3]) & password.equals((textData[4]))) {
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
		ArrayList<String> data = new ArrayList<String>();

		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;

			while((line = br.readLine()) != null) {
				data.add(line);
			}
			
			for(int i = 0; i < data.size(); i++) {
				textData = data.get(i).split(",");
				if(c.getfName().equals(textData[0])) {		//Unique ID	
					textData[5] = (Integer.toString(a.getBalance()));
					String nString = "";

					for(String x: textData) {
						nString += x + ",";
					}
					nString = nString.substring(0, nString.length() - 1);
					data.set(i, nString);
				}	
			}
			br.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))){
				for(int i = 0; i < data.size();i++) {
					bw.write(data.get(i)+ "\n");
				}
				bw.close();
				System.out.println("");
				System.out.println("");
				System.out.println("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}
}







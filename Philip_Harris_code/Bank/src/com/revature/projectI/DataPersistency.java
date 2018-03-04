package com.revature.projectI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataPersistency{
	static final String file = "src/database/Accounts.txt";
	static final String fileName = "src/database/AccountsSerialized.txt";
	static transient ArrayList<String> data = new ArrayList<String>();

	public void writeCustomer(Client Customer,Account a) {



		//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
		//			bw.write(Customer.toString() + "," + a.getBalance() + "\n");
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		data.add(Customer.toString() + "," + a.getBalance() + "\n");

		serialize(data);

		System.out.println("");
		System.out.println("");
		System.out.println("");

	}
	public boolean readCustomer(Client customer,Account account,String username, String password){

		String[] textData = null;
		

		deserilize();	
		for(String x : data) {
			textData = x.split(",");			
			if(username.equals(textData[3]) & password.equals((Validation.decode(textData[4])))) {
				customer.setfName(textData[0]);
				customer.setlName(textData[1]);
				customer.setSsn(Validation.dROT5(textData[2]));
				customer.setUsrName(textData[3]);
				customer.setPassword(Validation.decode(textData[4]));				
				account.setBalance(Validation.dROT5((textData[5])));
				return true;
			}

		}

		//		String[] textData = null;
		//
		//		try(BufferedReader br = new BufferedReader(new FileReader(file))){
		//			String line = null;
		//			
		//			while((line = br.readLine()) != null) {
		//				data.add(line);
		//			}
		//
		//
		//			while((line = br.readLine()) != null) {
		//				textData = line.split(",");	
		//				if(username.equals(textData[3]) & password.equals((Validation.decode(textData[4])))) {
		//					customer.setfName(textData[0]);
		//					customer.setlName(textData[1]);
		//					customer.setSsn(Validation.dROT5(textData[2]));
		//					customer.setUsrName(textData[3]);
		//					customer.setPassword(Validation.decode(textData[4]));
		//					account.setBalance(Validation.dROT5((textData[5])));
		//					return true;
		//				}	
		//			}
		//		} catch (FileNotFoundException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		return false;

	}


	public void updateBalance(Account a,Client c) {

		String[] textData = null;	
		int i = 0;
		
		deserilize();		
		for(String x: data) {
			textData = x.split(",");
			if(c.getfName().equals(textData[0])) {		//Unique ID	
				String bal = Integer.toString(a.getBalance());
				textData[5] = Integer.toString(Validation.eROT5(bal));
				String nString = "";

				for(String t: textData) {
					nString += t + ",";
				}
				nString = nString.substring(0, nString.length() - 1);
				data.set(i, nString);
			}
			i++;
		}
		
		serialize(data);


//		try(BufferedReader br = new BufferedReader(new FileReader(file))){
//			String line = null;
//
//			while((line = br.readLine()) != null) {
//				data.add(line);
//			}
//
//			for(int i = 0; i < data.size(); i++) {
//				textData = data.get(i).split(",");
//				if(c.getfName().equals(textData[0])) {		//Unique ID	
//					String bal = Integer.toString(a.getBalance());
//					textData[5] = Integer.toString(Validation.eROT5(bal));
//					String nString = "";
//
//					for(String x: textData) {
//						nString += x + ",";
//					}
//					nString = nString.substring(0, nString.length() - 1);
//					data.set(i, nString);
//				}	
//			}
//			br.close();
//		}catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))){
//			for(int i = 0; i < data.size();i++) {
//				bw.write(data.get(i)+ "\n");
//			}
//			bw.close();
//			System.out.println("");
//			System.out.println("");
//			System.out.println("");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
	}
	static void serialize(ArrayList<String> s) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){

			oos.writeObject(s);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void deserilize() {
		
		ArrayList<String> sData = new ArrayList<String>();
		
		try(ObjectInputStream ois = new ObjectInputStream( new FileInputStream(fileName))){
			sData = (ArrayList<String>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = sData;
	}
}







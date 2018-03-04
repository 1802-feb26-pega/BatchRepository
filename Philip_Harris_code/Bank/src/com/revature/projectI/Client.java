package com.revature.projectI;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	private String fName;
	private String lName;
	private String password;
	private int ssn;
	private String usrName;
	
	static DataPersistency db = new DataPersistency();
	static Account account = new Account();

	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	@Override
	public String toString() {
		return fName + "," + lName + "," + ssn + "," + usrName + "," + password;
	}
	
	public void addClient(ArrayList<String> info) {
			
		fName = info.get(0);
		lName = info.get(1);
		ssn = Validation.eROT5((info.get(2)));
		usrName = info.get(3);
		password = Validation.encode(info.get(4));
		account.setBalance(Validation.eROT5((info.get(5))));

		db.writeCustomer(this,account);
		System.out.println(fName + " " + lName + "  has been added successfully");
		System.out.println("Thank you for signing up with Revature bank!");
		System.out.println();
		System.out.println();
		
	}
	

}


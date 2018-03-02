package com.revature.projectI;

public class Validation {

	static DataPersistency db = new DataPersistency();
	
	public boolean validate(Client c,Account a,String usrname, String pass) {
		//Implement multiple login in attempts
		if(db.readCustomer(c,a,usrname, pass)) return true;
			//System.out.println("Welcome " + client.getfName() + " " + client.getlName());
		 	else {
		 		System.out.println("Access denied");
		 		System.out.println();
		 	}
		return false;
	}
	
}

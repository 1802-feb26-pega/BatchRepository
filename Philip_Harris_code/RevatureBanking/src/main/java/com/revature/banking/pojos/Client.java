package com.revature.banking.pojos;

import java.util.List;

import com.revature.banking.dao.Client_Dao;
import com.revature.banking.dao.DaoImpl;

public class Client {
	private String fName;
	private String lName;
	private String password;
	private int ssn;
	private String usrName;
	private static int Id;
	static DaoImpl dao = new DaoImpl();



	//Client constructor sets up new client
	public Client(String fName, String lName, int ssn, String usrName, String password) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.password = password;
		this.ssn = ssn;
		this.usrName = usrName;	
		

	}
	
	//Default constructor
	public Client() {
		// TODO Auto-generated constructor stub
	}

	//Getters and Setters
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
		
	public static int getId() {
		return Id;
	}

	public static void setId(int id) {
		Id = id;
	}

	//Customer toString  method
	@Override
	public String toString() {
		return fName + "," + lName + "," + ssn + "," + usrName + "," + password;
	}
	
	//Pass client information to DAO
	public static int write(Client C) {
		return Client_Dao.writeClient(C);
	}

	//Method that delAccount that user wants to delete
	public static boolean delAccount(Account account) {
		// TODO Auto-generated method stub
		if(dao.d_account(account)) return true;
		return false;
	}
	
	//Returns a list of usernames that prevent the from taking the same username
	public static boolean getClient(String username){
		List<Client> review =  dao.getClients();
		for(Client usr: review) {
			if(usr.getUsrName().equals(username)) return true;
		}
		return false;
	}


}

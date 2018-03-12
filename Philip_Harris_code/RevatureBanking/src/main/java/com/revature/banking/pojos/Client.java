package com.revature.banking.pojos;

import java.util.List;

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
}

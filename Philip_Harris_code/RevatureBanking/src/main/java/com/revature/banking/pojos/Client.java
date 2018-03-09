package com.revature.banking.pojos;

import com.revature.banking.dao.Client_Dao;

public class Client {
	private String fName;
	private String lName;
	private String password;
	private int ssn;
	private String usrName;
	private int Id;




	public Client(String fName, String lName, int ssn, String usrName, String password) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.password = password;
		this.ssn = ssn;
		this.usrName = usrName;	
		

	}
	
	public static int write(Client C) {
		return Client_Dao.writeClient(C);
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

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
		
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@Override
	public String toString() {
		return fName + "," + lName + "," + ssn + "," + usrName + "," + password;
	}
}

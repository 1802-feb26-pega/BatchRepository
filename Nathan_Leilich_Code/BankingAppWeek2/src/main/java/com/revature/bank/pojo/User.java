package com.revature.bank.pojo;

import java.util.ArrayList;
import java.util.Collection;

public class User {

	private String userName;
	private String password;
	private int userID;
	
////////////////////
////Constructors////
////////////////////

	public User() {};
	
	public User(String name, String pwd) {
		
		this.userName = name;
		this.password = pwd;
	}//constructor
	
	public User(int userID, String name, String pwd) {
		
		this.userID = userID;
		this.userName = name;
		this.password = pwd;
	}//constructor
	
//////////////////////////////////
////getters, setters, toString////
//////////////////////////////////	
	
	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}

	public String getUserName() {
		
		return userName;
	}
	
	public void setUserName(String userName) {
		
		this.userName = userName;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", userID=" + userID + "]";
	}

}//class

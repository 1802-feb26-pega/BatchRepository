package com.project.Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class AppMemory implements Serializable {
	private User user;
	private ArrayList<User> userDatabase;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<User> getUserDatabase() {
		return userDatabase;
	}
	public void setUserDatabase(ArrayList<User> userDatabase) {
		this.userDatabase = userDatabase;
	}
	
	

}

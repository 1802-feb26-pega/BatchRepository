package com.project.Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class AppMemory implements Serializable {
	private User user;
	private ArrayList<User> userList;
	
	public AppMemory() {
		userList = new ArrayList<User>();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "AppMemory [user=" + user + ", userList=" + userList + "]";
	}
	
	

}

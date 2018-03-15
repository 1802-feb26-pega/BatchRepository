package com.ex.services;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoServices {

	static ArrayList<User> users = new ArrayList<User>();
	static {
		users.add(new User("Joseph", "is amazing", "joseph.k.moses6@gmail.com"));
		users.add(new User("Peter", "is dope", "peter@gmail.com"));
		users.add(new User("Tiawan", "is cool", "tiawan@gmail.com"));
	}
	
	public ArrayList<User> getAll(){
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
}

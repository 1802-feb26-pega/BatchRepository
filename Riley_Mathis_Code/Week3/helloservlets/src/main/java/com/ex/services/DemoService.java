package com.ex.services;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService {

	
	static ArrayList<User> users = new ArrayList<User>();
	static {
		users.add(new User("name", "asdfa", "email"));
		users.add(new User("newname", "safd", "eadfallllllll"));
		users.add(new User("mnae", "hello", "some informations"));
	}
	
	public ArrayList<User> getAll(){
		return users;
	}
	public void addUser(User u) {
		users.add(u);
	}
}

package com.ex.service;

import java.util.ArrayList;
import com.ex.pojos.User;

public class DemoService {
	
	static ArrayList<User> users = new ArrayList<User>();
	static {
		users.add(new User("Will", "is awesome", "will@willmail.com"));
		users.add(new User("Brooks", "is not as awesome", "brooks@willmail.com"));
		users.add(new User("Elizabeth", "is is pink", "pink@willmail.com"));
	}
	
	public ArrayList<User> getAll() {
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
}

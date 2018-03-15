package com.ex.service;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService {
	static ArrayList<User> users = new ArrayList<>();
	static {
		users.add(new User("Peter", "the pumpkin eater", "peter@mail.com"));
		users.add(new User("Dog", "the bum sniffer", "dog@mail.com"));
		users.add(new User("Sheep", "the mutton chopper", "sheep@mail.com"));
	}
	
	public ArrayList<User>getAll() {
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
}

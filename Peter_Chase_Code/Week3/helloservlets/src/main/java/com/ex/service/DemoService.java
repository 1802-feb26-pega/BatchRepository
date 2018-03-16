package com.ex.service;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService {
	static ArrayList<User> users = new ArrayList<>();
	static {
		users.add(new User("Peter", "peter@mail.com", "password"));
		users.add(new User("Dog", "dog@mail.com", "password"));
		users.add(new User("Sheep", "sheep@mail.com", "password"));
	}
	
	public ArrayList<User>getAll() {
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
}

package com.ex.service;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService {

	static ArrayList<User> users = new ArrayList<User>();
	static {
		users.add(new User("Genesis", "is awesome", "genesis.bonds@gmail.com"));
		users.add(new User("Mark", "plays games", "mark.brandao@gmail.com"));
		users.add(new User("Pete", "has toppest tier memes", "peter.chase@gmail.com"));
	}
	
	public ArrayList<User> getAllUsers(){
		return users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	
	
	
	
	
}

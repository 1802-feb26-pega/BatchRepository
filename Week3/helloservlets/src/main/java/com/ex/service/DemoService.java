package com.ex.service;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService {
	
	static ArrayList<User> users = new ArrayList<User>();
	static {
		users.add(new User("Genesis", "is awesome", "genesis.bonds@gmail.com"));
		users.add(new User("Test User", "is not as awesome", "test@gmail.com"));
		users.add(new User("Beyonce", "is selling concert tix for 400+ dollars! Will JEsus be there??????????", "overpriced@gmail.com"));
	}
	
	public ArrayList<User> getAll(){
		return users;
	}
	
	public void addUser(User u) {
		users.add(u);
	}
	
	

}

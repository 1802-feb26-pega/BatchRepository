package com.ex.service;

import java.util.ArrayList;

import com.ex.pojos.User;

public class DemoService
{
	static ArrayList<User> users = new ArrayList<User>();
	
	static
	{
		users.add(new User("Genesis", "is awesome", "genesis.bonds@gmail.com"));
		users.add(new User("Tinuvial", "Morchaint", "tin@gmail.com"));
		users.add(new User("Lance", "Calvin", "lance@gmail.com"));
	}
	
	public ArrayList<User> getAll()
	{
		return users;
	}
	
	public void addUser(User u)
	{
		users.add(u);
	}
}

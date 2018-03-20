package com.bank.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.bank.pojos.Employee;
import com.bank.pojos.Event;

public interface DAO {
	
	public HashMap<Integer, String> getEmails();
	
	public Employee getUserById(int id);
	public Employee getUser(String email);
	public Employee addUser(Employee emp);
	
	public Event addEvent(Event event);
	public ArrayList<Event> getAllEvents();
	public ArrayList<Event> getEventByUser(Employee emp);
	
	public double getBalance(int id);
	public void updateBalance(int id, double amt);
	
	public void updateGrade(int id, String grade);
	
	public int getApproval(int id);
	void updateApproval(int id, int approval);
}

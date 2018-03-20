package com.ex.service;

import java.util.ArrayList;

import com.bank.dao.DAO;
import com.bank.dao.DAOImpl;
import com.bank.pojos.Employee;
import com.bank.pojos.Event;

public class Service {
	
	static DAO dao = new DAOImpl();
	
	public Employee login(String username, String password) {
		Employee user = dao.getUser(username);
		if(user== null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
	
	public Employee addUser(Employee emp) {
		return dao.addUser(emp);
	}
	
	public Event addEvent(Event event) {
		return dao.addEvent(event);
	}
	
	public boolean emailExists(String email) {
		Employee emp = dao.getUser(email);
		if(emp == null) return false;
		else return true;
	}
	
	public ArrayList<Event> getEvents(Employee emp) {
		ArrayList<Event> events = dao.getEventByUser(emp);
		return events;
	}
	
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = dao.getAllEvents();
		return events;
	}
	
	public void updateBalance(int id, int newBalance) {
		dao.updateBalance(id, newBalance);
	}
	
	public void updateGrade(int id, String grade) {
		dao.updateGrade(id, grade);
	}
	
	public void updateApproval(int id, int approval) {
		int old = dao.getApproval(id);
		approval = old + approval;
		dao.updateApproval(id, approval);
	}

}

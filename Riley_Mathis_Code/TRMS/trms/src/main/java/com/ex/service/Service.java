package com.ex.service;

import java.util.ArrayList;

import com.trms.dao.EmployeeDAO;
import com.trms.dao.EmployeeDAOImpl;
import com.trms.dao.EventDAO;
import com.trms.dao.EventDAOImpl;
import com.trms.dao.ReimbursementDAO;
import com.trms.dao.ReimbursementDAOImpl;
import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;

public class Service {
	
	static EmployeeDAO empDao = new EmployeeDAOImpl();
	static EventDAO evDao = new EventDAOImpl();
	static ReimbursementDAO reDao = new ReimbursementDAOImpl();
	
	public Employee login(String username, String password) {
		Employee employee = empDao.getEmployeeByUsername(username);
		if(employee== null) return null;
		else if (employee.getPassword().equals(password)) {
			return employee;
		}
		else return null;
	}
	
	public Reimbursement addReimbursement(Reimbursement r) {
		return reDao.addReimbursement(r.getEmployeeId(), r.getEventId(), r.getJustification(), r.getSuperApp(),
				r.getDepHeadApp(), r.getBenCoApp(), r.getRequestedAmount());
	}
	
	public Event addEvent(Event e) {
		return evDao.addEvent(e.getDateScheduled(), e.getEventLocation(), e.getEventCost(), e.getEventTypeId(), e.getEmployeeId());
	}
	
	public boolean usernameExists(String username) {
		Employee employee = empDao.getEmployeeByUsername(username);
		if(employee == null) return false;
		else return true;
	}
	
	public ArrayList<Event> getEvents (Employee e){
		return empDao.getEventsByEmployee(e);
	}
}

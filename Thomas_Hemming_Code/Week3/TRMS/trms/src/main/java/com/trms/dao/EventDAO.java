package com.trms.dao;

import java.sql.Date;
import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;

public interface EventDAO {

	public Event addEvent(Date date, String location, int cost, int typeId, int employeeId);
	
	public ArrayList<Event> getAllEvents();
	
	public Double getTotalReimbursements(int employeeId);
	
    public ArrayList<Event> getEventsByUser(Employee employee);
}

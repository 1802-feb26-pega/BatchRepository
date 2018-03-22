package com.trms.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;

public interface EventDAO {
	public Event addEvent(Timestamp date, String location, int cost, int typeId, int employeeId);
	
	public ArrayList<Event> getAllEvents();
	
	public Double getTotalReimbursements(int employeeId);
	
    public ArrayList<Event> getEventsByUser(Employee employee);
}

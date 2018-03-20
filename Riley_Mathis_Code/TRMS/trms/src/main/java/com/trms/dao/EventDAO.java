package com.trms.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.trms.pojos.Event;

public interface EventDAO {
//	public Event getEventById(int eventId);
//	public ArrayList<Event> getEventsByUser(Employee employee);
	public Event addEvent(Timestamp dateScheduled, String eventLocation, int eventCost,
			int eventTypeId, int employeeId);
}

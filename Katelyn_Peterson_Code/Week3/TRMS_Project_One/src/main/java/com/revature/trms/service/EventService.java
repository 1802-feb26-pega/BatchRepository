package com.revature.trms.service;

import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.dao.EventDAO;
import com.revature.trms.dao.EventDAOImpl;

public class EventService
{
	private static EventDAO eventDao = new EventDAOImpl();
	
	public ArrayList<String> getAllEvents()
	{
		Collection<String> transfer = eventDao.getAllEventTypes();
		ArrayList<String> output = (ArrayList<String>) transfer;
		
		return output;
	}
	
	// Takes in employee expense for event and calculates standard reimbursement
	public Double reimbursement(String event, Double cost)
	{
		Double percentReimburse = eventDao.getEventReimbursement(event);
		Double reimburse = cost * percentReimburse;
		
		return reimburse;
	}
}

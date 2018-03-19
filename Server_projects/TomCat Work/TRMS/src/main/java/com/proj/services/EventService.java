package com.proj.services;

import java.util.ArrayList;

import com.proj.pojos.Events;

public class EventService {

	public ArrayList<String> getEvents() {
		// TODO Auto-generated method stub
		EventDao dao = new EventDao();
		return dao.getEvents();
	}
}

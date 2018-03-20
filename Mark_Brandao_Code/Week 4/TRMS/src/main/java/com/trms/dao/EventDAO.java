package com.trms.dao;

import com.trms.pojos.Event;

public interface EventDAO {
	public Event addEvent(Event e);
	public Event getEventById(int eventId);
}

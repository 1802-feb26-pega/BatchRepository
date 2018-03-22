package com.project1.pojos;

public class Event {
	private int eventId;
	private String eventName;
	private int formatId;
	
	public Event() {
		
	}

	public Event(int eventId, String eventName, int formatId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.formatId = formatId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getFormatId() {
		return formatId;
	}

	public void setFormatId(int formatId) {
		this.formatId = formatId;
	}
	
	
}

package com.project1.pojos;

public class EventDetails {
	private int eventDetailsId;
	private int requestId;
	private int eventId;
	private String eventDate;
	private String eventTime;
	private int locationId;
	private double eventCost;
	private String eventDescription;
	private String justification;
	private int daysMissed;
	
	public EventDetails() {
		
	}
	
	public EventDetails(int eventDetailsId, int requestId, int eventId, String eventDate, String eventTime,
			int locationId, double eventCost, String eventDescription, String justification, int daysMissed) {
		super();
		this.eventDetailsId = eventDetailsId;
		this.requestId = requestId;
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.locationId = locationId;
		this.eventCost = eventCost;
		this.eventDescription = eventDescription;
		this.justification = justification;
		this.daysMissed = daysMissed;
	}

	public int getEventDetailsId() {
		return eventDetailsId;
	}

	public void setEventDetailsId(int eventDetailsId) {
		this.eventDetailsId = eventDetailsId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public double getEventCost() {
		return eventCost;
	}

	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getDaysMissed() {
		return daysMissed;
	}

	public void setDaysMissed(int daysMissed) {
		this.daysMissed = daysMissed;
	}
	
	
}

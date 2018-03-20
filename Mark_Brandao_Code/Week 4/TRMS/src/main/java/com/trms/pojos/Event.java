package com.trms.pojos;

import java.sql.Date;
import java.sql.Timestamp;

public class Event {
	private int eventId;
	private int eventCoverageId;
	private String eventLocation;
	private String description;
	private Date eventDate;
	private Timestamp eventTime;
	
	public Event() { }
	
	public Event(int eventId, int eventCoverageId, String eventLocation, String description, Date eventDate, Timestamp eventTime) {
		super();
		this.eventId = eventId;
		this.eventCoverageId = eventCoverageId;
		this.eventLocation = eventLocation;
		this.setDescription(description);
		this.eventDate = eventDate;
		this.eventTime = eventTime;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getEventCoverageId() {
		return eventCoverageId;
	}
	public void setEventCoverageId(int eventCoverageId) {
		this.eventCoverageId = eventCoverageId;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Timestamp getEventTime() {
		return eventTime;
	}
	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventCoverageId=" + eventCoverageId + ", eventLocation=" + eventLocation
				+ ", eventDate=" + eventDate + ", eventTime=" + eventTime + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

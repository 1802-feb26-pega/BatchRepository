package com.trms.pojos;

import java.sql.Timestamp;

public class Event {
	private int eventId;
	private Timestamp dateCreated;
	private Timestamp dateScheduled;
	private String eventLocation;
	private int eventCost;
	private int eventTypeId;
	private int employeeId;
	private int grade;
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(Timestamp dateScheduled, String eventLocation, int eventCost, int eventTypeId, int employeeId) {
		super();
		this.dateScheduled = dateScheduled;
		this.eventLocation = eventLocation;
		this.eventCost = eventCost;
		this.eventTypeId = eventTypeId;
		this.employeeId = employeeId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Timestamp getDateScheduled() {
		return dateScheduled;
	}
	public void setDateScheduled(Timestamp dateScheduled) {
		this.dateScheduled = dateScheduled;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public int getEventCost() {
		return eventCost;
	}
	public void setEventCost(int eventCost) {
		this.eventCost = eventCost;
	}
	public int getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateScheduled == null) ? 0 : dateScheduled.hashCode());
		result = prime * result + employeeId;
		result = prime * result + eventCost;
		result = prime * result + eventId;
		result = prime * result + eventTypeId;
		result = prime * result + grade;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateScheduled == null) {
			if (other.dateScheduled != null)
				return false;
		} else if (!dateScheduled.equals(other.dateScheduled))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (eventCost != other.eventCost)
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventTypeId != other.eventTypeId)
			return false;
		if (grade != other.grade)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", dateCreated=" + dateCreated + ", dateScheduled=" + dateScheduled
				+ ", eventCost=" + eventCost + ", eventTypeId=" + eventTypeId + ", employeeId=" + employeeId
				+ ", grade=" + grade + "]";
	}
	
	
}

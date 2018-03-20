package com.tailock.pojos;

import java.util.Date;

public class Request {

	private int request_id;
	private int employee_id;
	private String eventStartDate;
	private String dateOfRequest;
	private int timeOfEvent;
	private String location;
	private String description;
	private int costOfEvent;
	private int reimAmount;
	private int daysMissed;
	private String addedNote;
	private int gradeFormat;
	private int requestType;
	private int eventType;
	private int priority;
	private int status;
	
	
	public Request() {
		
	}
	
	public Request(int employee_id, String eventStartDate, int timeOfEvent, String location, String description,
			int costOfEvent, int gradeFormat, int requestType, int eventType) {
		super();
		this.employee_id = employee_id;
		this.eventStartDate = eventStartDate;
		this.timeOfEvent = timeOfEvent;
		this.location = location;
		this.description = description;
		this.costOfEvent = costOfEvent;
		this.gradeFormat = gradeFormat;
		this.requestType = requestType;
		this.eventType = eventType;
	}


	public int getRequest_id() {
		return request_id;
	}


	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}


	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}


	public String getEventStartDate() {
		return eventStartDate;
	}


	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}


	public String getDateOfRequest() {
		return dateOfRequest;
	}


	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}


	public int getTimeOfEvent() {
		return timeOfEvent;
	}


	public void setTimeOfEvent(int timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getCostOfEvent() {
		return costOfEvent;
	}


	public void setCostOfEvent(int costOfEvent) {
		this.costOfEvent = costOfEvent;
	}


	public int getReimAmount() {
		return reimAmount;
	}


	public void setReimAmount(int reimAmount) {
		this.reimAmount = reimAmount;
	}


	public int getDaysMissed() {
		return daysMissed;
	}


	public void setDaysMissed(int daysMissed) {
		this.daysMissed = daysMissed;
	}


	public String getAddedNote() {
		return addedNote;
	}


	public void setAddedNote(String addedNote) {
		this.addedNote = addedNote;
	}


	public int getGradeFormat() {
		return gradeFormat;
	}


	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}


	public int getRequestType() {
		return requestType;
	}


	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}


	public int getEventType() {
		return eventType;
	}


	public void setEventType(int eventType) {
		this.eventType = eventType;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}

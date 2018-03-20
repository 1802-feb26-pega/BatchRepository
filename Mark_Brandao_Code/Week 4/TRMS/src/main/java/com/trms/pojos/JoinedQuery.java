package com.trms.pojos;

import java.sql.Date;

public class JoinedQuery {
	private int reimbursementId;
	private Date dateSubmitted;
	private String justification;
	private int statusId;
	private int timeMissed;
	private double totalCost;
	private double projectedReimbursement;
	private int urgent;
	private String firstname;
	private String lastname;
	private String email;
	private String eventDescription;
	private String eventLocation;
	private Date eventDate;
	
	
	public JoinedQuery() { }
	
	public JoinedQuery(int reimbursementId, Date dateSubmitted, String justification, int statusId, int timeMissed,
			double totalCost, double projectedReimbursement, int urgent, String firstname, String lastname,
			String email, String eventDescription, String eventLocation, Date eventDate) {
		super();
		this.reimbursementId = reimbursementId;
		this.dateSubmitted = dateSubmitted;
		this.justification = justification;
		this.statusId = statusId;
		this.timeMissed = timeMissed;
		this.totalCost = totalCost;
		this.projectedReimbursement = projectedReimbursement;
		this.urgent = urgent;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.eventDescription = eventDescription;
		this.eventLocation = eventLocation;
		this.eventDate = eventDate;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTimeMissed() {
		return timeMissed;
	}
	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getProjectedReimbursement() {
		return projectedReimbursement;
	}
	public void setProjectedReimbursement(double projectedReimbursement) {
		this.projectedReimbursement = projectedReimbursement;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
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
	@Override
	public String toString() {
		return "JoinedQuery [reimbursementId=" + reimbursementId + ", dateSubmitted=" + dateSubmitted
				+ ", justification=" + justification + ", statusId=" + statusId + ", timeMissed=" + timeMissed
				+ ", totalCost=" + totalCost + ", projectedReimbursement=" + projectedReimbursement + ", urgent="
				+ urgent + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", eventDescription=" + eventDescription + ", eventLocation=" + eventLocation + ", eventDate="
				+ eventDate + "]";
	}
	
	
	
	
	
	
}

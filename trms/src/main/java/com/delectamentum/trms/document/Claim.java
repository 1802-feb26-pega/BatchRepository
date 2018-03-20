package com.delectamentum.trms.document;

import java.sql.Date;

public class Claim {
	private int id,
				employeeId,
				status,
				eventType,
				gradeType;
	
	private double amount,
				   minPassingGrade,
				   submittedGrade;
	
	private String location,
	   description,
	   comments,
	   justification,
	   eventTypeStr,
	   statusStr,
	   gradeTypeStr,
	   employeeName;
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getSubmittedGrade() {
		return submittedGrade;
	}

	public void setSubmittedGrade(double submittedGrade) {
		this.submittedGrade = submittedGrade;
	}
	
	public String getEventTypeStr() {
		return eventTypeStr;
	}

	public void setEventTypeStr(String eventTypeStr) {
		this.eventTypeStr = eventTypeStr;
	}

	private Date submitted,
				 eventDate;

	public Claim() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getGradeType() {
		return gradeType;
	}

	public void setGradeType(int gradeType) {
		this.gradeType = gradeType;
	}

	public double getMinPassingGrade() {
		return minPassingGrade;
	}

	public void setMinPassingGrade(double minPassingGrade) {
		this.minPassingGrade = minPassingGrade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getGradeTypeStr() {
		return gradeTypeStr;
	}

	public void setGradeTypeStr(String gradeTypeStr) {
		this.gradeTypeStr = gradeTypeStr;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", employeeId=" + employeeId + ", status=" + status + ", eventType=" + eventType
				+ ", gradeType=" + gradeType + ", amount=" + amount + ", minPassingGrade=" + minPassingGrade
				+ ", location=" + location + ", description=" + description + ", comments=" + comments
				+ ", justification=" + justification + ", eventTypeStr=" + eventTypeStr + ", statusStr=" + statusStr
				+ ", gradeTypeStr=" + gradeTypeStr + ", submitted=" + submitted + ", eventDate=" + eventDate + "]";
	}

	
}

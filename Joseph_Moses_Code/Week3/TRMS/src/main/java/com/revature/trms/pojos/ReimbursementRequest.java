package com.revature.trms.pojos;

import java.sql.Date;
import java.time.LocalDate;

public class ReimbursementRequest {
	private int rrId;
	private int empId;
	private Date requestDate;
	private Date startDate;
	private String location;
	private String description;
	private int typeOfEventId;
	private double cost;
	private int gradingFormatId;
	private int passingGrade;
	private String justification;
	private int workTimeMissed;
	private double expectedReimbursement;
	private int priorityId;
	private int statusId;
	
	public ReimbursementRequest() {}

	public ReimbursementRequest(int empId, Date requestDate, Date startDate, String location,
			String description, int typeOfEventId, double cost, int gradingFormatId, int passingGrade,
			String justification, int workTimeMissed, double expectedReimbursment, int priorityId, int statusId) {
		super();
		this.empId = empId;
		this.requestDate = requestDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.typeOfEventId = typeOfEventId;
		this.cost = cost;
		this.gradingFormatId = gradingFormatId;
		this.passingGrade = passingGrade;
		this.justification = justification;
		this.workTimeMissed = workTimeMissed;
		this.expectedReimbursement = expectedReimbursment;
		this.priorityId = priorityId;
		this.statusId = statusId;
	}

	public int getRrId() {
		return rrId;
	}

	public void setRrId(int rrId) {
		this.rrId = rrId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public int getTypeOfEventId() {
		return typeOfEventId;
	}

	public void setTypeOfEventId(int typeOfEventId) {
		this.typeOfEventId = typeOfEventId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getGradingFormatId() {
		return gradingFormatId;
	}

	public void setGradingFormatId(int gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getWorkTimeMissed() {
		return workTimeMissed;
	}

	public void setWorkTimeMissed(int workTimeMissed) {
		this.workTimeMissed = workTimeMissed;
	}

	public double getExpectedReimbursment() {
		return expectedReimbursement;
	}

	public void setExpectedReimbursment(double expectedReimbursment) {
		this.expectedReimbursement = expectedReimbursment;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "ReimbursementRequest [rrId=" + rrId + ", empId=" + empId + ", requestDate=" + requestDate
				+ ", startDate=" + startDate + ", location=" + location + ", description=" + description
				+ ", typeOfEventId=" + typeOfEventId + ", cost=" + cost + ", gradingFormatId=" + gradingFormatId
				+ ", passingGrade=" + passingGrade + ", justification=" + justification + ", workTimeMissed="
				+ workTimeMissed + ", expectedReimbursement=" + expectedReimbursement + ", priorityId=" + priorityId
				+ ", statusId=" + statusId + "]";
	}
	
	
	
}

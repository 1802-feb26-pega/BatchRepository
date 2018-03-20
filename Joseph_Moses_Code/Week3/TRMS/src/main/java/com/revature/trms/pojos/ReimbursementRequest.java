package com.revature.trms.pojos;

import java.sql.Date;
import java.time.LocalDate;

public class ReimbursementRequest {
	private int rrId;
	private Employee emp;
	private Date requestDate;
	private Date startDate;
	private String location;
	private String description;
	private TypeOfEvent typeOfEvent;
	private double cost;
	private GradingFormat gradingFormat;
	private int passingGrade;
	private String justification;
	private int workTimeMissed;
	private double expectedReimbursement;
	private Priority priority;
	private Status status;
	
	public ReimbursementRequest() {}

	public ReimbursementRequest(Employee emp, Date requestDate, Date startDate, String location, String description,
			TypeOfEvent typeOfEvent, double cost, GradingFormat gradingFormat, int passingGrade, String justification,
			int workTimeMissed, double expectedReimbursement, Priority priority, Status status) {
		super();
		this.emp = emp;
		this.requestDate = requestDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.typeOfEvent = typeOfEvent;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
		this.justification = justification;
		this.workTimeMissed = workTimeMissed;
		this.expectedReimbursement = expectedReimbursement;
		this.priority = priority;
		this.status = status;
	}

	public int getRrId() {
		return rrId;
	}

	public void setRrId(int rrId) {
		this.rrId = rrId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
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

	public TypeOfEvent getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setTypeOfEvent(TypeOfEvent typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
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

	public double getExpectedReimbursement() {
		return expectedReimbursement;
	}

	public void setExpectedReimbursement(double expectedReimbursement) {
		this.expectedReimbursement = expectedReimbursement;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbursementRequest [rrId=" + rrId + ", emp=" + emp + ", requestDate=" + requestDate + ", startDate="
				+ startDate + ", location=" + location + ", description=" + description + ", typeOfEvent=" + typeOfEvent
				+ ", cost=" + cost + ", gradingFormat=" + gradingFormat + ", passingGrade=" + passingGrade
				+ ", justification=" + justification + ", workTimeMissed=" + workTimeMissed + ", expectedReimbursement="
				+ expectedReimbursement + ", priority=" + priority + ", status=" + status + "]";
	}

	
}

package com.trms.pojos;

public class TRForm {
	private int formId = -1;
	private int employeeId;
	private int eventType;
	private String date;
	private String time;
	private String location;
	private String reason;
	private double missedWorkTime;
	private double cost;
	private int gsId = -1;
	private Reimbursement reimbursement = null;
	private String name = "";
	
	public TRForm(int employeeId, int eventType, String date, String time, String location, double missedWorkTime,
			double cost, String reason) {
		this.employeeId = employeeId;
		this.eventType = eventType;
		this.date = date;
		this.time = time;
		this.location = location;
		this.missedWorkTime = missedWorkTime;
		this.cost = cost;
		this.reason = reason;
	}
	
	public TRForm() {
		this.employeeId = -1;
		this.eventType = -1;
		this.date = "";
		this.time = "";
		this.location = "";
		this.missedWorkTime = -1;
		this.cost = -1;
		this.reason = "";
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getMissedWorkTime() {
		return missedWorkTime;
	}

	public void setMissedWorkTime(double missedWorkTime) {
		this.missedWorkTime = missedWorkTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getGsId() {
		return gsId;
	}

	public void setGsId(int gsId) {
		this.gsId = gsId;
	}

	public Reimbursement getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}

	@Override
	public String toString() {
		return "TRForm [formId=" + formId + ", employeeId=" + employeeId + ", eventType=" + eventType + ", date=" + date
				+ ", time=" + time + ", location=" + location + ", reason=" + reason + ", missedWorkTime="
				+ missedWorkTime + ", cost=" + cost + ", gsId=" + gsId + "]";
	}

	
	
	
	
}

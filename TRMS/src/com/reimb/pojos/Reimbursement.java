package com.reimb.pojos;

import java.sql.*;

public class Reimbursement {
	private int reimb_id;
	private int emp_id;
    private Date event_date;
    private String city;
    private String state;
    private double cost;
    private double proj_reimburse;
    private String description;
    private int format_id;
    private int event_type;
    private int attach_id;
    private int approval_id;
    private int hours_missed;
    
    public int getReimbId() {
		return reimb_id;
	}
	public void setReimbId(int reimbId) {
		this.reimb_id = reimbId;
	}
	public int getEmpId() {
		return emp_id;
	}
	public void setEmpId(int empId) {
		this.emp_id = empId;
	}
	public Date getEventDateTime() {
		return event_date;
	}
	public void setEventDateTime(Date event_date) {
		this.event_date = event_date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getProjectedReimbursement() {
		return proj_reimburse;
	}
	public void setProjectedReimbursement(double proj_reimburse) {
		this.proj_reimburse = proj_reimburse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFormatId() {
		return format_id;
	}
	public void setFormatId(int formatId) {
		this.format_id = formatId;
	}
	public int getEventType() {
		return event_type;
	}
	public void setEventType(int eventType) {
		this.event_type = eventType;
	}
	public int getAttachId() {
		return attach_id;
	}
	public void setAttachId(int attachId) {
		this.attach_id = attachId;
	}
	public int getApprovalId() {
		return approval_id;
	}
	public void setApprovalId(int approvalId) {
		this.approval_id = approvalId;
	}
	public int getHoursMissed() {
		return hours_missed;
	}
	public void setHoursMissed(int hoursMissed) {
		this.hours_missed = hoursMissed;
	}
	
}

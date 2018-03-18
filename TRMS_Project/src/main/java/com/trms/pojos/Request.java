package com.trms.pojos;

import java.sql.Date;
import java.sql.Timestamp;

public class Request {
	private int requestId;
	private String eventType;
	private Date startDate;
	private Date endDate;
	private String location;
	private String description;
	private double cost;
	private int gradingStyleId;
	private int grade;
	private Date requestDate;
	private Timestamp requestTime;
	private int flaggedId;
	private int approvalId;
	
	public Request() {}
	
	public Request(int rId,String eType,String sDate,String eDate,String loc,String desc,double cost,
			int gStyleId,int grade,String rDate,String t,int flaggedId,int appId)
	{
		super();
		this.requestId = rId;
		this.eventType = eType;
		this.startDate = Date.valueOf(sDate);
		this.endDate = Date.valueOf(eDate);
		this.location = loc;
		this.description = desc;
		this.cost = cost;
		this.gradingStyleId = gStyleId;
		this.grade = grade;
		this.requestDate = Date.valueOf(rDate);
		this.requestTime = Timestamp.valueOf(t);
		this.flaggedId = flaggedId;
		this.approvalId = appId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getGradingStyleId() {
		return gradingStyleId;
	}

	public void setGradingStyleId(int gradingStyleId) {
		this.gradingStyleId = gradingStyleId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}

	public int getFlaggedId() {
		return flaggedId;
	}

	public void setFlaggedId(int flaggedId) {
		this.flaggedId = flaggedId;
	}

	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", eventType=" + eventType + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", location=" + location + ", description=" + description + ", cost=" + cost
				+ ", gradingStyleId=" + gradingStyleId + ", grade=" + grade + ", requestDate=" + requestDate
				+ ", requestTime=" + requestTime + ", flaggedId=" + flaggedId + ", approvalId=" + approvalId + "]";
	}
	
	
	
}

package com.project1.pojos;

import java.sql.Blob;
import java.sql.Date;

public class Requests {
	
	private int requestsId;
	private int employeeId;
	private String requestsStatus;
	private String requestsType;
	private String requestsGradingFormat;
	private String requestsDate;
	private String requestsLocation;
	private double requestsCost;
	private String requestsDescription;
	private String requestsJustification;
	private int requestsDaysMissed;
	private String requestsAttachments;
	
	public Requests() {
		
	}
	
	public Requests(int requestsId, int employeeId, String requestsStatus, String requestsType, String requestsGradingFormat,
			String requestsDate, String requestsLocation, double requestsCost, String requestsDescription,
			String requestsJustification, int requestsDaysMissed, String requestsAttachments) {
		super();
		this.requestsId = requestsId;
		this.requestsStatus = requestsStatus;
		this.requestsType = requestsType;
		this.requestsGradingFormat = requestsGradingFormat;
		this.requestsDate = requestsDate;
		this.requestsLocation = requestsLocation;
		this.requestsCost = requestsCost;
		this.requestsDescription = requestsDescription;
		this.requestsJustification = requestsJustification;
		this.requestsDaysMissed = requestsDaysMissed;
		this.requestsAttachments = requestsAttachments;
	}

	public int getRequestsId() {
		return requestsId;
	}

	public void setRequestsId(int requestsId) {
		this.requestsId = requestsId;
	}

	public String getRequestsStatus() {
		return requestsStatus;
	}

	public void setRequestsStatus(String requestsStatus) {
		this.requestsStatus = requestsStatus;
	}

	public String getRequestsType() {
		return requestsType;
	}

	public void setRequestsType(String requestsType) {
		this.requestsType = requestsType;
	}

	public String getRequestsGradingFormat() {
		return requestsGradingFormat;
	}

	public void setRequestsGradingFormat(String requestsGradingFormat) {
		this.requestsGradingFormat = requestsGradingFormat;
	}

	public String getRequestsDate() {
		return requestsDate;
	}

	public void setRequestsDate(String requestsDate) {
		this.requestsDate = requestsDate;
	}

	public String getRequestsLocation() {
		return requestsLocation;
	}

	public void setRequestsLocation(String requestsLocation) {
		this.requestsLocation = requestsLocation;
	}

	public double getRequestsCost() {
		return requestsCost;
	}

	public void setRequestsCost(double requestsCost) {
		this.requestsCost = requestsCost;
	}

	public String getRequestsDescription() {
		return requestsDescription;
	}

	public void setRequestsDescription(String requestsDescription) {
		this.requestsDescription = requestsDescription;
	}

	public String getRequestsJustification() {
		return requestsJustification;
	}

	public void setRequestsJustification(String requestsJustification) {
		this.requestsJustification = requestsJustification;
	}

	public int getRequestsDaysMissed() {
		return requestsDaysMissed;
	}

	public void setRequestsDaysMissed(int requestsDaysMissed) {
		this.requestsDaysMissed = requestsDaysMissed;
	}

	public String getRequestsAttachments() {
		return requestsAttachments;
	}

	public void setRequestsAttachments(String requestsAttachments) {
		this.requestsAttachments = requestsAttachments;
	}

	@Override
	public String toString() {
		return "Requests [requestsId=" + requestsId + ", requestsStatus=" + requestsStatus + ", requestsType="
				+ requestsType + ", requestsGradingFormat=" + requestsGradingFormat + ", requestsDate=" + requestsDate
				+ ", requestsLocation=" + requestsLocation + ", requestsCost=" + requestsCost + ", requestsDescription="
				+ requestsDescription + ", requestsJustification=" + requestsJustification + ", requestsDaysMissed="
				+ requestsDaysMissed + ", requestsAttachments=" + requestsAttachments + "]";
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}

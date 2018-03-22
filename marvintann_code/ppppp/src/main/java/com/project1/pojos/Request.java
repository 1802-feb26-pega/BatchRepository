package com.project1.pojos;

import java.sql.Timestamp;

public class Request {
	private int requestId;
	private int employeeId;
	private Timestamp requestTime;
	
	public Request() {
		
	}
	
	public Request(int requestId, int employeeId, Timestamp requestTime) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.requestTime = requestTime;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Timestamp getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Timestamp timestamp) {
		this.requestTime = timestamp;
	}

	
	
}

package com.project1.pojos;

public class RequestStatus {
	private int requestStatusId;
	private int requestId;
	private String requestStatusType;
	
	public RequestStatus() {
		
	}

	public RequestStatus(int requestStatusId, int requestId, String requestStatusType) {
		super();
		this.requestStatusId = requestStatusId;
		this.requestId = requestId;
		this.requestStatusType = requestStatusType;
	}

	public int getRequestStatusId() {
		return requestStatusId;
	}

	public void setRequestStatusId(int requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestStatusType() {
		return requestStatusType;
	}

	public void setRequestStatusType(String requestStatusType) {
		this.requestStatusType = requestStatusType;
	}
	
	
}

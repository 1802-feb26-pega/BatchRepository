package com.trms.pojos;

public class ReimbursementStatus {
	private int statusId;
	private String statusDescription;
	
	public ReimbursementStatus() { }
	
	public ReimbursementStatus(int statusId, String statusDescription) {
		super();
		this.statusId = statusId;
		this.statusDescription = statusDescription;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	@Override
	public String toString() {
		return "ReimbursementStatus [statusId=" + statusId + ", statusDescription=" + statusDescription + "]";
	}
	
}

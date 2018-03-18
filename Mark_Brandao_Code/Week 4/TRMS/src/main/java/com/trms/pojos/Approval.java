package com.trms.pojos;

import java.sql.Timestamp;

public class Approval {
	private int approvalId;
	private Timestamp approvalTimestamp;
	private int approvalTypeId;
	private String approvalJustification;
	private int approvedByEmployeeId;
	
	public Approval() { }
	
	public Approval(int approvalId, Timestamp approvalTimestamp, int approvalTypeId, String approvalJustification,
			int approvedByEmployeeId) {
		super();
		this.approvalId = approvalId;
		this.approvalTimestamp = approvalTimestamp;
		this.approvalTypeId = approvalTypeId;
		this.approvalJustification = approvalJustification;
		this.approvedByEmployeeId = approvedByEmployeeId;
	}
	public int getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
	public Timestamp getApprovalTimestamp() {
		return approvalTimestamp;
	}
	public void setApprovalTimestamp(Timestamp approvalTimestamp) {
		this.approvalTimestamp = approvalTimestamp;
	}
	public int getApprovalTypeId() {
		return approvalTypeId;
	}
	public void setApprovalTypeId(int approvalTypeId) {
		this.approvalTypeId = approvalTypeId;
	}
	public String getApprovalJustification() {
		return approvalJustification;
	}
	public void setApprovalJustification(String approvalJustification) {
		this.approvalJustification = approvalJustification;
	}
	public int getApprovedByEmployeeId() {
		return approvedByEmployeeId;
	}
	public void setApprovedByEmployeeId(int approvedByEmployeeId) {
		this.approvedByEmployeeId = approvedByEmployeeId;
	}
	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", approvalTimestamp=" + approvalTimestamp + ", approvalTypeId="
				+ approvalTypeId + ", approvalJustification=" + approvalJustification + ", approvedByEmployeeId="
				+ approvedByEmployeeId + "]";
	}
}

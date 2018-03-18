package com.trms.pojos;

public class ApprovalType {
	private int approvalTypeId;
	private String approvalTypeDescription;
	
	public ApprovalType() { }
	
	public ApprovalType(int approvalTypeId, String approvalTypeDescription) {
		super();
		this.approvalTypeId = approvalTypeId;
		this.approvalTypeDescription = approvalTypeDescription;
	}
	public int getApprovalTypeId() {
		return approvalTypeId;
	}
	public void setApprovalTypeId(int approvalTypeId) {
		this.approvalTypeId = approvalTypeId;
	}
	public String getApprovalTypeDescription() {
		return approvalTypeDescription;
	}
	public void setApprovalTypeDescription(String approvalTypeDescription) {
		this.approvalTypeDescription = approvalTypeDescription;
	}
	@Override
	public String toString() {
		return "ApprovalType [approvalTypeId=" + approvalTypeId + ", approvalTypeDescription=" + approvalTypeDescription
				+ "]";
	}
}

package com.trms.pojos;

public class Approval {
	private int approvalId;
	private int requestId;
	private int supervisorId;
	private int deptHeadId;
	private int bencoId;
	private String supervisorStatus;
	private String deptHeadStatus;
	private String bencoStatus;
	private double approvalAmount;
	
	public Approval() {}
	
	public Approval(int a, int r, int s, int d, int b, String sStatus, String dStatus,
			String bStatus, double amount)
	{
		super();
		this.approvalId=a;
		this.requestId=r;
		this.supervisorId=s;
		this.deptHeadId=d;
		this.bencoId=b;
		this.supervisorStatus = sStatus;
		this.deptHeadStatus = dStatus;
		this.bencoStatus = bStatus;
		this.approvalAmount = amount;
	}//constructor

	public int getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(int deptHeadId) {
		this.deptHeadId = deptHeadId;
	}

	public int getBencoId() {
		return bencoId;
	}

	public void setBencoId(int bencoId) {
		this.bencoId = bencoId;
	}

	public String getSupervisorStatus() {
		return supervisorStatus;
	}

	public void setSupervisorStatus(String supervisorStatus) {
		this.supervisorStatus = supervisorStatus;
	}

	public String getDeptHeadStatus() {
		return deptHeadStatus;
	}

	public void setDeptHeadStatus(String deptHeadStatus) {
		this.deptHeadStatus = deptHeadStatus;
	}

	public String getBencoStatus() {
		return bencoStatus;
	}

	public void setBencoStatus(String bencoStatus) {
		this.bencoStatus = bencoStatus;
	}

	public double getApprovalAmount() {
		return approvalAmount;
	}

	public void setApprovalAmount(double approvalAmount) {
		this.approvalAmount = approvalAmount;
	}

	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", requestId=" + requestId + ", supervisorId=" + supervisorId
				+ ", deptHeadId=" + deptHeadId + ", bencoId=" + bencoId + ", supervisorStatus=" + supervisorStatus
				+ ", deptHeadStatus=" + deptHeadStatus + ", bencoStatus=" + bencoStatus + ", approvalAmount="
				+ approvalAmount + "]";
	}
	
}//approval

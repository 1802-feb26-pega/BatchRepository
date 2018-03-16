package com.trms.pojos;

public class DepartmentHead extends Employee{

	private int myDepHeadId;

	public DepartmentHead() {
		super();
		this.myDepHeadId = -1;
	}

	public DepartmentHead(int emp_id,int myDepHeadId, String firstName, String lastName, double paidReimbursments, int supervisorId,
			int depHeadId, int benCoId, String department, String jobTitle, String username, String password) {
		super(emp_id, firstName, lastName, paidReimbursments, supervisorId, depHeadId, benCoId, department, jobTitle, username,
				password);
		this.myDepHeadId = myDepHeadId;
	}

	public DepartmentHead(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		this.myDepHeadId = -1;
	}

	public int getMyDepHeadId() {
		return myDepHeadId;
	}

	public void setMyDepHeadId(int myDepHeadId) {
		this.myDepHeadId = myDepHeadId;
	}

	@Override
	public String toString() {
		return "DepartmentHead [myDepHeadId=" + myDepHeadId + ", id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", paidReimbursments=" + paidReimbursments + ", supervisorId=" + supervisorId
				+ ", depHeadId=" + depHeadId + ", benCoId=" + benCoId + ", department=" + department + ", jobTitle="
				+ jobTitle + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}

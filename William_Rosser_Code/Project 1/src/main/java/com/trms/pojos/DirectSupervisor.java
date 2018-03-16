package com.trms.pojos;

public class DirectSupervisor extends Employee{

	private int dsId = -1;

	public DirectSupervisor() {
		super();
		
	}

	public DirectSupervisor(int emp_id, int ds_id, String firstName, String lastName, double paidReimbursments, int supervisorId,
			int depHeadId, int benCoId, String department, String jobTitle, String username, String password) {
		super(emp_id, firstName, lastName, paidReimbursments, supervisorId, depHeadId, benCoId, department, jobTitle, username,
				password);
		this.dsId = ds_id;
		
	}

	public DirectSupervisor(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
	}

	@Override
	public String toString() {
		return "DirectSupervisor [ds_id=" + dsId + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", paidReimbursments=" + paidReimbursments + ", supervisorId=" + supervisorId + ", depHeadId="
				+ depHeadId + ", benCoId=" + benCoId + ", department=" + department + ", jobTitle=" + jobTitle
				+ ", username=" + username + ", password=" + password + "]";
	}

	public int getDsId() {
		return dsId;
	}

	public void setDsId(int dsId) {
		this.dsId = dsId;
	}
	
	
	
	
}

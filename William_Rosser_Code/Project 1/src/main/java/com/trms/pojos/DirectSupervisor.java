package com.trms.pojos;

public class DirectSupervisor extends Employee{

	private int ds_id;

	public DirectSupervisor() {
		super();
		
	}

	public DirectSupervisor(int emp_id, int ds_id, String firstName, String lastName, double paidReimbursments, int supervisorId,
			int depHeadId, int benCoId, String department, String jobTitle, String username, String password) {
		super(emp_id, firstName, lastName, paidReimbursments, supervisorId, depHeadId, benCoId, department, jobTitle, username,
				password);
		this.ds_id = ds_id;
		
	}

	public DirectSupervisor(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		this.ds_id = -1;
	}

	@Override
	public String toString() {
		return "DirectSupervisor [ds_id=" + ds_id + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", paidReimbursments=" + paidReimbursments + ", supervisorId=" + supervisorId + ", depHeadId="
				+ depHeadId + ", benCoId=" + benCoId + ", department=" + department + ", jobTitle=" + jobTitle
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}

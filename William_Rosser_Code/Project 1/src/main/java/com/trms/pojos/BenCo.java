package com.trms.pojos;

public class BenCo extends Employee{

	private int benCoId;

	public BenCo() {
		super();
		this.benCoId = -1;
	}

	public BenCo(int uid, int benCoId, String firstName, String lastName, double paidReimbursments, int supervisorId, int depHeadId,
			int altBenCoId, String department, String jobTitle, String username, String password) {
		super(uid, firstName, lastName, paidReimbursments, supervisorId, depHeadId, altBenCoId, department, jobTitle, username,
				password);
		this.benCoId = benCoId;
	}

	public BenCo(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		this.benCoId = -1;
	}

	public int getBenCoId() {
		return benCoId;
	}

	public void setBenCoId(int benCoId) {
		this.benCoId = benCoId;
	}

	@Override
	public String toString() {
		return "BenCo [benCoId=" + benCoId + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", paidReimbursments=" + paidReimbursments + ", supervisorId=" + supervisorId + ", depHeadId="
				+ depHeadId + ", department=" + department + ", jobTitle=" + jobTitle + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	

}

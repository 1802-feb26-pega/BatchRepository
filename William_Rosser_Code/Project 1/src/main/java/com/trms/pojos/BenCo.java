package com.trms.pojos;

public class BenCo extends Employee{

	private int myBenCoId;

	public BenCo() {
		super();
		this.myBenCoId = -1;
	}

	public BenCo(int uid, int benCoId, String firstName, String lastName, double paidReimbursments, int supervisorId, int depHeadId,
			int altBenCoId, String department, String jobTitle, String username, String password) {
		super(uid, firstName, lastName, paidReimbursments, supervisorId, depHeadId, altBenCoId, department, jobTitle, username,
				password);
		this.myBenCoId = benCoId;
	}

	public BenCo(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		this.myBenCoId = -1;
	}

	public int getMyBenCoId() {
		return myBenCoId;
	}

	public void setMyBenCoId(int benCoId) {
		this.myBenCoId = benCoId;
	}

	@Override
	public String toString() {
		return "BenCo [myBenCoId=" + myBenCoId + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", paidReimbursments=" + paidReimbursments + ", supervisorId=" + supervisorId + ", depHeadId="
				+ depHeadId + ", department=" + department + ", jobTitle=" + jobTitle + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	

}

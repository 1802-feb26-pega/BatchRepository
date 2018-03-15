package com.trms.pojos;

public class BenCo extends Employee{

	private int benCoId;

	public BenCo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BenCo(int id, String firstName, String lastName, double paidReimbursments, int supervisorId, int depHeadId,
			int benCoId, String department, String jobTitle, String username, String password) {
		super(id, firstName, lastName, paidReimbursments, supervisorId, depHeadId, benCoId, department, jobTitle, username,
				password);
		// TODO Auto-generated constructor stub
	}

	public BenCo(String firstName, String lastName, String username, String password) {
		super(firstName, lastName, username, password);
		// TODO Auto-generated constructor stub
	}
	
	
	

}

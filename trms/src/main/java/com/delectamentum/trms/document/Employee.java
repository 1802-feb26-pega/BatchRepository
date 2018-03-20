package com.delectamentum.trms.document;

public class Employee {
	
	public static final int STANDARD = 1,
							SUPERVISOR = 2,
							DEPTHEAD = 3,
							BENCO = 4;
	
	private int id,
				supervisorid,
				type,
				department;

	private String firstname,
				   lastname,
				   email,
				   password;
	
	private double amtReimbursed,
				   amtPending;	
	
	public Employee() {}

	public Employee(int id, int supervisorid, int type, int department, String firstname, String lastname, String email,
			String password, double amtReimbursed, double amtPending) {
		super();
		this.id = id;
		this.supervisorid = supervisorid;
		this.type = type;
		this.department = department;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.amtReimbursed = amtReimbursed;
		this.amtPending = amtPending;
	}

	public Employee(int id, int supervisorid, int type, String firstname, String lastname, String email,
			String password, double amtReimbursed, double amtPending) {
		super();
		this.id = id;
		this.supervisorid = supervisorid;
		this.type = type;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.amtReimbursed = amtReimbursed;
		this.amtPending = amtPending;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupervisorid() {
		return supervisorid;
	}

	public void setSupervisorid(int supervisorid) {
		this.supervisorid = supervisorid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAmtReimbursed() {
		return amtReimbursed;
	}

	public void setAmtReimbursed(double amtReimbursed) {
		this.amtReimbursed = amtReimbursed;
	}

	public double getAmtPending() {
		return amtPending;
	}

	public void setAmtPending(double amtPending) {
		this.amtPending = amtPending;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", supervisorid=" + supervisorid + ", type=" + type + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", amtReimbursed="
				+ amtReimbursed + ", amtPending=" + amtPending + "]";
	}

	
	
}

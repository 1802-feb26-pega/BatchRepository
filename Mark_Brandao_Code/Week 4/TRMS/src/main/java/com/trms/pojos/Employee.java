package com.trms.pojos;

public class Employee {
	private int employeeId;
	private String firstname;
	private String lastname;
	private String email;
	private String pass;
	private int supervisorId;
	private int departmentId;
	private int levelId;
	private double availableReimbursement;
	private double pendingReimbursement;
	private double awardedReimbursement;
	
	public Employee() { }
	
	public Employee(int employeeId, String firstname, String lastname, String email, String pass, int supervisorId,
			int departmentId, int levelId, double availableReimbursement, double pendingReimbursement, double awardedReimbursement) {
		super();
		this.employeeId = employeeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.pass = pass;
		this.supervisorId = supervisorId;
		this.departmentId = departmentId;
		this.levelId = levelId;
		this.availableReimbursement = availableReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.awardedReimbursement = awardedReimbursement;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public double getAvailableReimbursement() {
		return availableReimbursement;
	}
	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}
	public double getPendingReimbursement() {
		return pendingReimbursement;
	}
	public void setPendingReimbursement(double pendingReimbursement) {
		this.pendingReimbursement = pendingReimbursement;
	}
	public double getAwardedReimbursement() {
		return awardedReimbursement;
	}
	public void setAwardedReimbursement(double awardedReimbursement) {
		this.awardedReimbursement = awardedReimbursement;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", pass=" + pass + ", supervisorId=" + supervisorId + ", departmentId=" + departmentId
				+ ", levelId=" + levelId + ", availableReimbursement=" + availableReimbursement
				+ ", pendingReimbursement=" + pendingReimbursement + ", awardedReimbursement=" + awardedReimbursement
				+ "]";
	}
}

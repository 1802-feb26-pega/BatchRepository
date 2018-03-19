package com.reimb.pojos;

import java.util.Date;

public class Employee {
	private int emp_id;
	private String first_name;
	private String last_name;
	private String password;
	private double reimburse_remain;
	private double reimburse_pend;
	private String email;
	private Date date_of_birth;
	private int job_level;
	private int zip;
	
	public Employee(){}

	public int getEmpId() {
		return emp_id;
	}

	public void setEmpId(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getReimburseRemaining() {
		return reimburse_remain;
	}

	public void setReimburseRemain(double reimburse_remain) {
		this.reimburse_remain = reimburse_remain;
	}
	
	public double getReimbursePending() {
		return reimburse_pend;
	}
	
	public void setReimbursePending(double reimburse_pend) {
		this.reimburse_pend = reimburse_pend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDOB() {
		return date_of_birth;
	}

	public void setDOB(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public int getJobLevel() {
		return job_level;
	}

	public void setJobLevel(int job_level) {
		this.job_level = job_level;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", firstname=" + first_name + ", lastname=" + last_name + ", email=" + email
				+ ", password=" + password + "]";
	}

}

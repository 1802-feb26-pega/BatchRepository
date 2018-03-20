package com.reimb.pojos;

import java.time.LocalDate;

public class Employee {
	private int emp_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private double reimburse_remain;
	private double reimburse_pend;
	private LocalDate date_of_birth;
	private int job_level;
	private int department;
	private String address;
	private String city;
	private String state;
	
	public Employee(){}

	public Employee(int id, String first_name, String last_name, String email, String password, double reimburse_remain,
			double reimburse_pend, LocalDate DOB, int job_level, int department, String address, String city, String state) {
		super();
		this.emp_id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.reimburse_remain = reimburse_remain;
		this.reimburse_pend = reimburse_pend;
		this.date_of_birth = DOB;
		this.job_level = job_level;
		this.department = department;
		this.address = address;
		this.city = city;
		this.state = state;
	}
	
	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public LocalDate getDOB() {
		return date_of_birth;
	}

	public void setDOB(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public int getJobLevel() {
		return job_level;
	}

	public void setJobLevel(int job_level) {
		this.job_level = job_level;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", firstname=" + first_name + ", lastname=" + last_name + ", email=" + email
				+ ", password=" + password + "]";
	}

}

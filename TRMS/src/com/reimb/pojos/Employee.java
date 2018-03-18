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

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getReimburse_remain() {
		return reimburse_remain;
	}

	public void setReimburse_remain(double reimburse_remain) {
		this.reimburse_remain = reimburse_remain;
	}
	
	public double getReimburse_pend() {
		return reimburse_pend;
	}
	
	public void setReimburse_pend(double reimburse_pend) {
		this.reimburse_pend = reimburse_pend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public int getJob_level() {
		return job_level;
	}

	public void setJob_level(int job_level) {
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

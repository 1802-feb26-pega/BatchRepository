package com.trms.pojos;

import java.sql.Date;

public class User {
	
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	//private Date birthdate;
	private String address;
	private String zipcode;
	private String title;
	private int supervisorId;
	private int deptHeadId;
	private int benCoId;
	private double approvedFunds;
	private double pendingFunds;
	
	
	public User(){}
	
	public User(String firstname, String lastname, String email, String password,
			String birthdate,String address,String zipcode,String title,int supId,int headId,
			int benId,double approved, double pending) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = email;
		this.password = password;
		//this.birthdate=Date.valueOf(birthdate);
		this.address=address;
		this.zipcode=zipcode;
		this.title=title;
		this.supervisorId=supId;
		this.deptHeadId=headId;
		this.benCoId=benId;
		this.approvedFunds=approved;
		this.pendingFunds=pending;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String uname) {
		this.username = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*
	public String getBirthdate() {
		return birthdate.toString();
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = Date.valueOf(birthdate);
	}
*/
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(int deptHeadId) {
		this.deptHeadId = deptHeadId;
	}

	public int getBenCoId() {
		return benCoId;
	}

	public void setBenCoId(int benCoId) {
		this.benCoId = benCoId;
	}

	public double getApprovedFunds() {
		return approvedFunds;
	}

	public void setApprovedFunds(double approvedFunds) {
		this.approvedFunds = approvedFunds;
	}

	public double getPendingFunds() {
		return pendingFunds;
	}

	public void setPendingFunds(double pendingFunds) {
		this.pendingFunds = pendingFunds;
	}
/*
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", birthdate=" + birthdate + ", address=" + address + ", zipcode="
				+ zipcode + ", title=" + title + ", supervisorId=" + supervisorId + ", deptHeadId=" + deptHeadId
				+ ", benCoId=" + benCoId + ", approvedFunds=" + approvedFunds + ", pendingFunds=" + pendingFunds + "]";
	}
*/

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", address=" + address + ", zipcode=" + zipcode + ", title=" + title
				+ ", supervisorId=" + supervisorId + ", deptHeadId=" + deptHeadId + ", benCoId=" + benCoId
				+ ", approvedFunds=" + approvedFunds + ", pendingFunds=" + pendingFunds + "]";
	}
	
	
}

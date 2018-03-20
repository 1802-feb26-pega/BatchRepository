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
	private int departmentId;
	private double approvedFunds;
	private double pendingFunds;
	
	
	public User(){}
	
	public User(String firstname, String lastname, String email, String password,
			String birthdate,String address,String zipcode,String title,int dept,double approved, double pending) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = email;
		this.password = password;
		//this.birthdate=Date.valueOf(birthdate);
		this.address=address;
		this.zipcode=zipcode;
		this.title=title;
		this.departmentId=dept;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int dept) {
		this.departmentId = dept;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", address=" + address + ", zipcode=" + zipcode + ", title=" + title
				+ ", departmentId=" + departmentId + ", approvedFunds=" + approvedFunds + ", pendingFunds="
				+ pendingFunds + "]";
	}
	
	
}

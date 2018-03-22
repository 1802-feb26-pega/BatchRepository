package com.project1.pojos;

public class Employee {
	private int employeeId;
	private int titleId;
	private int departmentId;
	private String firstName;
	private String lastName;
	private int addressId;
	private String email;
	private String pwd;
	private int reportsTo;
	
	public Employee() {
		
	}
	
	public Employee(int employeeId, int titleId, int departmentId, String firstName, String lastName, int addressId,
			String email, String pwd, int reportsTo) {
		super();
		this.employeeId = employeeId;
		this.titleId = titleId;
		this.departmentId = departmentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressId = addressId;
		this.email = email;
		this.pwd = pwd;
		this.reportsTo = reportsTo;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	
}

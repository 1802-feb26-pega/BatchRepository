package com.delectamentum.trms.controller;

class RegisterRequest {
	private String firstname,
		   lastname,
		   email,
		   password,
		   supervisorf,
		   supervisorl;
	
	private int type,
				departmentId;
	
	public RegisterRequest() {}

	public RegisterRequest(String firstname, String lastname, String email, String passwprd, String supervisorf,
			String supervisorl, int type) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = passwprd;
		this.supervisorf = supervisorf;
		this.supervisorl = supervisorl;
		this.type = type;
	}

	public RegisterRequest(String firstname, String lastname, String email, String password, String supervisorf,
			String supervisorl, int type, int departmentId) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.supervisorf = supervisorf;
		this.supervisorl = supervisorl;
		this.type = type;
		this.departmentId = departmentId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getSupervisorf() {
		return supervisorf;
	}

	public String getSupervisorl() {
		return supervisorl;
	}

	public int getType() {
		return type;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String passwprd) {
		this.password = passwprd;
	}

	public void setSupervisorf(String supervisorf) {
		this.supervisorf = supervisorf;
	}

	public void setSupervisorl(String supervisorl) {
		this.supervisorl = supervisorl;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RegisterRequest [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
				+ password + ", supervisorf=" + supervisorf + ", supervisorl=" + supervisorl + ", type=" + type + "]";
	}
	
}
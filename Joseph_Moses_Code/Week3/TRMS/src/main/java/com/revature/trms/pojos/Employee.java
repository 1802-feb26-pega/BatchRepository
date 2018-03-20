package com.revature.trms.pojos;

public class Employee {

	private int empId;
	private String fName;
	private String lName;
	private String phone;
	private String email;
	private String password;
	private int superId;
	private int deptId;
	private int empLevel;
	private double amountAvailable;
	private double maxAvailable;
	private double pending;
	private double awarded;
	
	public Employee() {}

	public Employee(String fName, String lName, String phone, String email, String password, int superId, int deptId,
			int empLevel, double amountAvailable, double maxAvailable, double pending, double awarded) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.superId = superId;
		this.deptId = deptId;
		this.empLevel = empLevel;
		this.amountAvailable = amountAvailable;
		this.maxAvailable = maxAvailable;
		this.pending = pending;
		this.awarded = awarded;
	}


	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getSuperId() {
		return superId;
	}

	public void setSuperId(int superId) {
		this.superId = superId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(int empLevel) {
		this.empLevel = empLevel;
	}

	public double getAmountAvailable() {
		return amountAvailable;
	}

	public void setAmountAvailable(double amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

	public double getMaxAvailable() {
		return maxAvailable;
	}

	public void setMaxAvailable(double maxAvailable) {
		this.maxAvailable = maxAvailable;
	}

	public double getPending() {
		return pending;
	}

	public void setPending(double pending) {
		this.pending = pending;
	}

	public double getAwarded() {
		return awarded;
	}

	public void setAwarded(double awarded) {
		this.awarded = awarded;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", phone=" + phone + ", email="
				+ email + ", password=" + password + ", superId=" + superId + ", deptId=" + deptId + ", empLevel="
				+ empLevel + ", amountAvailable=" + amountAvailable + ", maxAvailable=" + maxAvailable + ", pending="
				+ pending + ", awarded=" + awarded + "]";
	}
	
	
}

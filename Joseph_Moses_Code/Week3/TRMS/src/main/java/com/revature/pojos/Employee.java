package com.revature.pojos;

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
	private int employeeReimbursmentId;
	
	public Employee() {}

	public Employee(int empId, String fName, String lName, String phone, String email, String password, int superId,
			int deptId, int empLevel, int employeeReimbursment) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.superId = superId;
		this.deptId = deptId;
		this.empLevel = empLevel;
		this.employeeReimbursmentId = employeeReimbursment;
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

	public int getEmployeeReimbursment() {
		return employeeReimbursmentId;
	}

	public void setEmployeeReimbursment(int employeeReimbursment) {
		this.employeeReimbursmentId = employeeReimbursment;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", phone=" + phone + ", email="
				+ email + ", superId=" + superId + ", deptId=" + deptId + ", empLevel=" + empLevel
				+ ", employeeReimbursment=" + employeeReimbursmentId + "]";
	}

	
	
	
	
}

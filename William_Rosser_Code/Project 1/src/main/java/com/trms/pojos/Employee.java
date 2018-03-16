package com.trms.pojos;

public class Employee implements User {

	protected int id;
	protected String firstName;
	protected String lastName;
	protected double paidReimbursments = 0;
	protected int supervisorId;
	protected int depHeadId;
	protected int benCoId;
	protected String department;
	protected String jobTitle;
	protected String username;
	protected String password;




	public Employee(int id, String firstName, String lastName, double paidReimbursments, int supervisorId,
			int depHeadId, int benCoId, String department, String jobTitle, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.paidReimbursments = paidReimbursments;
		this.supervisorId = supervisorId;
		this.depHeadId = depHeadId;
		this.benCoId = benCoId;
		this.department = department;
		this.jobTitle = jobTitle;
		this.username = username;
		this.password = password;
	}

	public Employee(String firstName, String lastName, String username, String password) {
		super();
		this.id = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.paidReimbursments = 0;
		this.supervisorId = -1;
		this.depHeadId = -1;
		this.benCoId = -1;
		this.department = "";
		this.jobTitle = "";
		this.username = username;
		this.password = password;
	}

	public Employee() {
		this.id = -1;
		this.firstName = "";
		this.lastName = "";
		this.paidReimbursments = 0;
		this.supervisorId = -1;
		this.depHeadId = -1;
		this.benCoId = -1;
		this.department = "";
		this.jobTitle = "";
		this.username = "";
		this.password = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getPaidReimbursments() {
		return paidReimbursments;
	}

	public void setPaidReimbursments(double paidReimbursments) {
		this.paidReimbursments = paidReimbursments;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getDepHeadId() {
		return depHeadId;
	}

	public void setDepHeadId(int depHeadId) {
		this.depHeadId = depHeadId;
	}

	public int getBenCoId() {
		return benCoId;
	}

	public void setBenCoId(int benCoId) {
		this.benCoId = benCoId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getWholeName() {
		return firstName + " " + lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + benCoId;
		result = prime * result + depHeadId;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(paidReimbursments);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + supervisorId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (benCoId != other.benCoId)
			return false;
		if (depHeadId != other.depHeadId)
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (Double.doubleToLongBits(paidReimbursments) != Double.doubleToLongBits(other.paidReimbursments))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (supervisorId != other.supervisorId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", paidReimbursments="
				+ paidReimbursments + ", supervisorId=" + supervisorId + ", depHeadId=" + depHeadId + ", benCoId="
				+ benCoId + ", department=" + department + ", jobTitle=" + jobTitle + ", username=" + username
				+ ", password=" + password + "]";
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}
}

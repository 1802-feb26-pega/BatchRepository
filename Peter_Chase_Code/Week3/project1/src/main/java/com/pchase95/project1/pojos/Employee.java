package com.pchase95.project1.pojos;

public class Employee implements TrmsObject {
	public static final double STARTING_BALANCE = 1000.00;
	
	private long id;
	private Employee superVisor;
	private Employee departmentHead;
	private Department department;
	private Location location;
	private boolean isDepartmentHead;
	private String email;
	private String password;
	
	private double avaliableReimbursment;
	
	public Employee() {
		
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", superVisor=" + superVisor.getId() + ", departmentHead=" + departmentHead.getId()
				+ ", department=" + department + ", location=" + location + ", isDepartmentHead=" + isDepartmentHead
				+ ", email=" + email + ", password=" + password + ", avaliableReimbursment=" + avaliableReimbursment
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avaliableReimbursment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((departmentHead == null) ? 0 : departmentHead.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isDepartmentHead ? 1231 : 1237);
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((superVisor == null) ? 0 : superVisor.hashCode());
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
		if (Double.doubleToLongBits(avaliableReimbursment) != Double.doubleToLongBits(other.avaliableReimbursment))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (departmentHead == null) {
			if (other.departmentHead != null)
				return false;
		} else if (!departmentHead.equals(other.departmentHead))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (isDepartmentHead != other.isDepartmentHead)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (superVisor == null) {
			if (other.superVisor != null)
				return false;
		} else if (!superVisor.equals(other.superVisor))
			return false;
		return true;
	}

	
	public Employee getDepartmentHead() {
		return departmentHead;
	}
	
	
	
	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getSuperVisor() {
		return superVisor;
	}

	public void setSuperVisor(Employee superVisor) {
		this.superVisor = superVisor;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isDepartmentHead() {
		return isDepartmentHead;
	}

	public void setIsDepartmentHead(boolean isDepartmentHead) {
		this.isDepartmentHead = isDepartmentHead;
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

	public double getAvaliableReimbursment() {
		return avaliableReimbursment;
	}

	public void setAvaliableReimbursment(double avaliableReimbursment) {
		this.avaliableReimbursment = avaliableReimbursment;
	}
	
	
}

package com.revature.trms.pojos;

public class Employee
{
	// Private Variables
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private Double reimburse;
	private Integer reportsto;
	private Integer departId;
	private String empTitle;
	
	// Constructors
	public Employee()
	{
		super();
		this.employeeId = 0;
	}

	public Employee(Integer employeeId, String firstName, String lastName, String email, String password, String phone,
			Double reimburse, Integer reportsto, Integer departId, String empTitle)
	{
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.reimburse = reimburse;
		this.reportsto = reportsto;
		this.departId = departId;
		this.empTitle = empTitle;
	}
	
	// Getters
	public Integer getEmployeeId()
	{
		return employeeId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public String getPhone()
	{
		return phone;
	}

	public Double getReimburse()
	{
		return reimburse;
	}

	public Integer getReportsto()
	{
		return reportsto;
	}

	public Integer getDepartId()
	{
		return departId;
	}

	public String getEmpTitle()
	{
		return empTitle;
	}
	
	// Setters
	public void setEmployeeId(Integer employeeId)
	{
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setReimburse(Double reimburse)
	{
		this.reimburse = reimburse;
	}

	public void setReportsto(Integer reportsto)
	{
		this.reportsto = reportsto;
	}

	public void setDepartId(Integer departId)
	{
		this.departId = departId;
	}

	public void setEmpTitle(String empTitle)
	{
		this.empTitle = empTitle;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departId == null) ? 0 : departId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((empTitle == null) ? 0 : empTitle.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reimburse == null) ? 0 : reimburse.hashCode());
		result = prime * result + ((reportsto == null) ? 0 : reportsto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (departId == null)
		{
			if (other.departId != null)
				return false;
		}
		else if (!departId.equals(other.departId))
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (empTitle == null)
		{
			if (other.empTitle != null)
				return false;
		}
		else if (!empTitle.equals(other.empTitle))
			return false;
		if (employeeId == null)
		{
			if (other.employeeId != null)
				return false;
		}
		else if (!employeeId.equals(other.employeeId))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		}
		else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		}
		else if (!lastName.equals(other.lastName))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (phone == null)
		{
			if (other.phone != null)
				return false;
		}
		else if (!phone.equals(other.phone))
			return false;
		if (reimburse == null)
		{
			if (other.reimburse != null)
				return false;
		}
		else if (!reimburse.equals(other.reimburse))
			return false;
		if (reportsto == null)
		{
			if (other.reportsto != null)
				return false;
		}
		else if (!reportsto.equals(other.reportsto))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", phone=" + phone + ", reimburse=" + reimburse + ", reportsto="
				+ reportsto + ", departId=" + departId + ", empTitle=" + empTitle + "]";
	}
	
}

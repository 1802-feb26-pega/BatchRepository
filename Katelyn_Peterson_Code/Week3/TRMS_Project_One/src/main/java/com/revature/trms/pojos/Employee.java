package com.revature.trms.pojos;

public class Employee
{
	// Private Variables
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private Integer postal;
	private String phone;
	private Double reimburse;
	
	// Constructors
	public Employee()
	{
		super();
	}

	public Employee(String firstName, String lastName, String address, String city, String state, Integer postal,
			String phone, Double reimburse)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.phone = phone;
		this.reimburse = reimburse;
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

	public String getAddress()
	{
		return address;
	}

	public String getCity()
	{
		return city;
	}

	public String getState()
	{
		return state;
	}

	public Integer getPostal()
	{
		return postal;
	}

	public String getPhone()
	{
		return phone;
	}

	public Double getReimburse()
	{
		return reimburse;
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

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public void setPostal(Integer postal)
	{
		this.postal = postal;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public void setReimburse(Double reimburse)
	{
		this.reimburse = reimburse;
	}
	
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
		result = prime * result + ((reimburse == null) ? 0 : reimburse.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (address == null)
		{
			if (other.address != null)
				return false;
		}
		else if (!address.equals(other.address))
			return false;
		if (city == null)
		{
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
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
		if (phone == null)
		{
			if (other.phone != null)
				return false;
		}
		else if (!phone.equals(other.phone))
			return false;
		if (postal == null)
		{
			if (other.postal != null)
				return false;
		}
		else if (!postal.equals(other.postal))
			return false;
		if (reimburse == null)
		{
			if (other.reimburse != null)
				return false;
		}
		else if (!reimburse.equals(other.reimburse))
			return false;
		if (state == null)
		{
			if (other.state != null)
				return false;
		}
		else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", postal=" + postal + ", phone="
				+ phone + ", reimburse=" + reimburse + "]";
	}
}

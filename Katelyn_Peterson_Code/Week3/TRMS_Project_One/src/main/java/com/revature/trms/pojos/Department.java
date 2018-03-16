package com.revature.trms.pojos;

public class Department
{
	// Private Variables
	private Integer departmentId;
	private Integer departHeadId;
	private String departName;
	
	// Constructor
	public Department()
	{
		super();
	}

	public Department(Integer departmentId, Integer departHeadId, String departName)
	{
		super();
		this.departmentId = departmentId;
		this.departHeadId = departHeadId;
		this.departName = departName;
	}
	
	// Getters
	public Integer getDepartmentId()
	{
		return departmentId;
	}

	public Integer getDepartHeadId()
	{
		return departHeadId;
	}

	public String getDepartName()
	{
		return departName;
	}
	
	// Setters
	public void setDepartmentId(Integer departmentId)
	{
		this.departmentId = departmentId;
	}

	public void setDepartHeadId(Integer departHeadId)
	{
		this.departHeadId = departHeadId;
	}

	public void setDepartName(String departName)
	{
		this.departName = departName;
	}
	
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departHeadId == null) ? 0 : departHeadId.hashCode());
		result = prime * result + ((departName == null) ? 0 : departName.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
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
		Department other = (Department) obj;
		if (departHeadId == null)
		{
			if (other.departHeadId != null)
				return false;
		}
		else if (!departHeadId.equals(other.departHeadId))
			return false;
		if (departName == null)
		{
			if (other.departName != null)
				return false;
		}
		else if (!departName.equals(other.departName))
			return false;
		if (departmentId == null)
		{
			if (other.departmentId != null)
				return false;
		}
		else if (!departmentId.equals(other.departmentId))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Department [departmentId=" + departmentId + ", departHeadId=" + departHeadId + ", departName="
				+ departName + "]";
	}
	
}

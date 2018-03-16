package com.revature.trms.pojos;

public class Form
{
	// Private Variables
	private Integer formId;
	private Integer employId;
	private String address;
	private String city;
	private String state;
	private Integer postal;
	private Double cost;
	private String event;
	private String gradeFormat;
	// Query server for time, also see if submit time can be truncated on default
	private boolean dsApproval;
	private boolean dhApproval;
	private boolean benCoApproval;
	private String grade;
	private Double award;
	
	// Constructors
	public Form()
	{
		super();
	}

	public Form(Integer formId, Integer employId, String address, String city, String state, Integer postal,
			Double cost, String event, String gradeFormat, boolean dsApproval, boolean dhApproval, boolean benCoApproval,
			String grade, Double award)
	{
		super();
		this.formId = formId;
		this.employId = employId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.cost = cost;
		this.event = event;
		this.gradeFormat = gradeFormat;
		this.dsApproval = dsApproval;
		this.dhApproval = dhApproval;
		this.benCoApproval = benCoApproval;
		this.grade = grade;
		this.award = award;
	}
	
	// Getters
	public Integer getFormId()
	{
		return formId;
	}

	public Integer getEmployId()
	{
		return employId;
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

	public Double getCost()
	{
		return cost;
	}

	public String getEvent()
	{
		return event;
	}

	public String getGradeFormat()
	{
		return gradeFormat;
	}

	public boolean isDsApproval()
	{
		return dsApproval;
	}

	public boolean isDhApproval()
	{
		return dhApproval;
	}

	public boolean isBenCoApproval()
	{
		return benCoApproval;
	}

	public String getGrade()
	{
		return grade;
	}

	public Double getAward()
	{
		return award;
	}
	
	// Setters
	public void setFormId(Integer formId)
	{
		this.formId = formId;
	}

	public void setEmployId(Integer employId)
	{
		this.employId = employId;
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

	public void setCost(Double cost)
	{
		this.cost = cost;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public void setGradeFormat(String gradeFormat)
	{
		this.gradeFormat = gradeFormat;
	}

	public void setDsApproval(boolean dsApproval)
	{
		this.dsApproval = dsApproval;
	}

	public void setDhApproval(boolean dhApproval)
	{
		this.dhApproval = dhApproval;
	}

	public void setBenCoApproval(boolean benCoApproval)
	{
		this.benCoApproval = benCoApproval;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public void setAward(Double award)
	{
		this.award = award;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((award == null) ? 0 : award.hashCode());
		result = prime * result + (benCoApproval ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + (dhApproval ? 1231 : 1237);
		result = prime * result + (dsApproval ? 1231 : 1237);
		result = prime * result + ((employId == null) ? 0 : employId.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((gradeFormat == null) ? 0 : gradeFormat.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
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
		Form other = (Form) obj;
		if (address == null)
		{
			if (other.address != null)
				return false;
		}
		else if (!address.equals(other.address))
			return false;
		if (award == null)
		{
			if (other.award != null)
				return false;
		}
		else if (!award.equals(other.award))
			return false;
		if (benCoApproval != other.benCoApproval)
			return false;
		if (city == null)
		{
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (cost == null)
		{
			if (other.cost != null)
				return false;
		}
		else if (!cost.equals(other.cost))
			return false;
		if (dhApproval != other.dhApproval)
			return false;
		if (dsApproval != other.dsApproval)
			return false;
		if (employId == null)
		{
			if (other.employId != null)
				return false;
		}
		else if (!employId.equals(other.employId))
			return false;
		if (event == null)
		{
			if (other.event != null)
				return false;
		}
		else if (!event.equals(other.event))
			return false;
		if (formId == null)
		{
			if (other.formId != null)
				return false;
		}
		else if (!formId.equals(other.formId))
			return false;
		if (grade == null)
		{
			if (other.grade != null)
				return false;
		}
		else if (!grade.equals(other.grade))
			return false;
		if (gradeFormat == null)
		{
			if (other.gradeFormat != null)
				return false;
		}
		else if (!gradeFormat.equals(other.gradeFormat))
			return false;
		if (postal == null)
		{
			if (other.postal != null)
				return false;
		}
		else if (!postal.equals(other.postal))
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
		return "Form [formId=" + formId + ", employId=" + employId + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", postal=" + postal + ", cost=" + cost + ", event=" + event + ", gradeFormat="
				+ gradeFormat + ", dsApproval=" + dsApproval + ", dhApproval=" + dhApproval + ", benCoApproval="
				+ benCoApproval + ", grade=" + grade + ", award=" + award + "]";
	}
}

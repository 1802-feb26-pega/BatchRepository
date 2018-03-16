package unused;

public class Grade
{
	// Private Variables
	private String gradeType;
	
	// Constructors
	public Grade()
	{
		super();
	}

	public Grade(String gradeType)
	{
		super();
		this.gradeType = gradeType;
	}
	
	// Getters
	public String getGradeType()
	{
		return gradeType;
	}
	
	// Setters
	public void setGradeType(String gradeType)
	{
		this.gradeType = gradeType;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gradeType == null) ? 0 : gradeType.hashCode());
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
		Grade other = (Grade) obj;
		if (gradeType == null)
		{
			if (other.gradeType != null)
				return false;
		}
		else if (!gradeType.equals(other.gradeType))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Grade [gradeType=" + gradeType + "]";
	}
}

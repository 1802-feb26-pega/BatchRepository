package unused;

public class AttachmentType
{
	// Private Variables
	private String attachType;
	
	// Constructors
	public AttachmentType()
	{
		super();
	}

	public AttachmentType(String attachType)
	{
		super();
		this.attachType = attachType;
	}
	
	// Getters
	public String getAttachType()
	{
		return attachType;
	}
	
	// Setters
	public void setAttachType(String attachType)
	{
		this.attachType = attachType;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachType == null) ? 0 : attachType.hashCode());
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
		AttachmentType other = (AttachmentType) obj;
		if (attachType == null)
		{
			if (other.attachType != null)
				return false;
		}
		else if (!attachType.equals(other.attachType))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "AttachmentType [attachType=" + attachType + "]";
	}
	
}

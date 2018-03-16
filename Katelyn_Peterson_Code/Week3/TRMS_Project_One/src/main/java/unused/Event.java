package unused;

public class Event
{
	// Private Variables
	private String eventType;
	private Integer reimbursePercent;
	
	// Constructors
	public Event()
	{
		super();
	}
	
	public Event(String eventType, Integer reimbursePercent)
	{
		super();
		this.eventType = eventType;
		this.reimbursePercent = reimbursePercent;
	}
	
	// Getters
	public String getEventType()
	{
		return eventType;
	}

	public Integer getReimbursePercent()
	{
		return reimbursePercent;
	}
	
	// Setters
	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}

	public void setReimbursePercent(Integer reimbursePercent)
	{
		this.reimbursePercent = reimbursePercent;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((reimbursePercent == null) ? 0 : reimbursePercent.hashCode());
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
		Event other = (Event) obj;
		if (eventType == null)
		{
			if (other.eventType != null)
				return false;
		}
		else if (!eventType.equals(other.eventType))
			return false;
		if (reimbursePercent == null)
		{
			if (other.reimbursePercent != null)
				return false;
		}
		else if (!reimbursePercent.equals(other.reimbursePercent))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Event [eventType=" + eventType + ", reimbursePercent=" + reimbursePercent + "]";
	}
}

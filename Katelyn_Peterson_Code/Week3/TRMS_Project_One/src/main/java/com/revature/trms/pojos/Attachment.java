package com.revature.trms.pojos;

import java.util.Arrays;

public class Attachment
{
	// Private Variables
	private Integer attachId;
	private Integer formId;
	private String attachType;
	private byte[] attach;
	
	// Constructors
	public Attachment()
	{
		super();
	}

	public Attachment(Integer attachId, Integer formId, String attachType, byte[] attach)
	{
		super();
		this.attachId = attachId;
		this.formId = formId;
		this.attachType = attachType;
		this.attach = attach;
	}

	public Integer getAttachId() {
		return attachId;
	}
	
	// Getters
	public Integer getFormId()
	{
		return formId;
	}

	public String getAttachType()
	{
		return attachType;
	}

	public byte[] getAttach()
	{
		return attach;
	}
	
	// Setters
	public void setAttachId(Integer attachId)
	{
		this.attachId = attachId;
	}

	public void setFormId(Integer formId)
	{
		this.formId = formId;
	}

	public void setAttachType(String attachType)
	{
		this.attachType = attachType;
	}

	public void setAttach(byte[] attach)
	{
		this.attach = attach;
	}
	
	// Other Functions
	
	// Overrides
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attach);
		result = prime * result + ((attachId == null) ? 0 : attachId.hashCode());
		result = prime * result + ((attachType == null) ? 0 : attachType.hashCode());
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
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
		Attachment other = (Attachment) obj;
		if (!Arrays.equals(attach, other.attach))
			return false;
		if (attachId == null)
		{
			if (other.attachId != null)
				return false;
		}
		else if (!attachId.equals(other.attachId))
			return false;
		if (attachType == null)
		{
			if (other.attachType != null)
				return false;
		}
		else if (!attachType.equals(other.attachType))
			return false;
		if (formId == null)
		{
			if (other.formId != null)
				return false;
		}
		else if (!formId.equals(other.formId))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Attachment [attachId=" + attachId + ", formId=" + formId + ", attachType=" + attachType + ", attach="
				+ Arrays.toString(attach) + "]";
	}
	
}

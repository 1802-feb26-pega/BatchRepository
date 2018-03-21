package com.pchase95.project1.pojos;

public class ApprovalType implements TrmsObject {
	public static final int PENDING = 1;
	public static final int DENIED = 2;
	public static final int ACCEPTED = 3;
	public static final int EXCEEDING = 4;
	
	private long id;
	private String status;
	
	public ApprovalType() {
		
	}

	@Override
	public String toString() {
		return "ApprovalType [id=" + id + ", status=" + status + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApprovalType other = (ApprovalType) obj;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

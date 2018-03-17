package com.pchase95.trms.pojos;

public class ReimbursmentType implements TrmsObject {
	private long id;
	private String name;
	private float coverage;
	
	public ReimbursmentType() {
		
	}
	
	

	@Override
	public String toString() {
		return "ReimbursmentType [id=" + id + ", name=" + name + ", coverage=" + coverage + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(coverage);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ReimbursmentType other = (ReimbursmentType) obj;
		if (Float.floatToIntBits(coverage) != Float.floatToIntBits(other.coverage))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCoverage() {
		return coverage;
	}

	public void setCoverage(float coverage) {
		this.coverage = coverage;
	}
	
	
}

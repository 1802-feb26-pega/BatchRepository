package com.pchase95.trms.pojos;

public class GradingFormat implements TrmsObject {
	private long id;
	private String name;
	private String cutoff;
	
	public GradingFormat() {
		
	}
	

	@Override
	public String toString() {
		return "GradingFormat [id=" + id + ", name=" + name + ", cutoff=" + cutoff + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cutoff == null) ? 0 : cutoff.hashCode());
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
		GradingFormat other = (GradingFormat) obj;
		if (cutoff == null) {
			if (other.cutoff != null)
				return false;
		} else if (!cutoff.equals(other.cutoff))
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

	public String getCutoff() {
		return cutoff;
	}

	public void setCutoff(String cutoff) {
		this.cutoff = cutoff;
	}
	
	
}

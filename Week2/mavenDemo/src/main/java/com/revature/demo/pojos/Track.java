package com.revature.demo.pojos;

public class Track {
	
	private int id;
	private String name;
	private int albumid;
	private int mediatypeid;
	private int genreid;
	private String composer;
	private int milliseconds;
	private int bytes;
	private double unitprice;
	
	public Track() {
		
	}
	
	public Track(int id, String name, int albumid, int mediatypeid, int genreid, 
			String composer, int milliseconds, int bytes, double unitprice) {
		super();
		this.id = id;
		this.name = name;
		this.albumid = albumid;
		this.mediatypeid = mediatypeid;
		this.genreid = genreid;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitprice = unitprice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public int getMediatypeid() {
		return mediatypeid;
	}

	public void setMediatypeid(int mediatypeid) {
		this.mediatypeid = mediatypeid;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	@Override
	public String toString() {
		return "Track [id=" + id + ", name=" + name + ", albumid=" + albumid + ", mediatypeid=" + mediatypeid
				+ ", genreid=" + genreid + ", composer=" + composer + ", milliseconds=" + milliseconds + ", bytes="
				+ bytes + ", unitprice=" + unitprice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + albumid;
		result = prime * result + bytes;
		result = prime * result + ((composer == null) ? 0 : composer.hashCode());
		result = prime * result + genreid;
		result = prime * result + id;
		result = prime * result + mediatypeid;
		result = prime * result + milliseconds;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(unitprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Track other = (Track) obj;
		if (albumid != other.albumid)
			return false;
		if (bytes != other.bytes)
			return false;
		if (composer == null) {
			if (other.composer != null)
				return false;
		} else if (!composer.equals(other.composer))
			return false;
		if (genreid != other.genreid)
			return false;
		if (id != other.id)
			return false;
		if (mediatypeid != other.mediatypeid)
			return false;
		if (milliseconds != other.milliseconds)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(unitprice) != Double.doubleToLongBits(other.unitprice))
			return false;
		return true;
	}

}

package com.trms.pojos;

public class Employee {
	private int id;
	private String firstname;
	private String lastname;
	private int position;
	private String username;
	private int awardedRe;
	private int pendingRe;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String firstname, String lastname, int position, String username) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.position = position;
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAwardedRe() {
		return awardedRe;
	}
	public void setAwardedRe(int awardedRe) {
		this.awardedRe = awardedRe;
	}
	public int getPendingRe() {
		return pendingRe;
	}
	public void setPendingRe(int pendingRe) {
		this.pendingRe = pendingRe;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + awardedRe;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + pendingRe;
		result = prime * result + position;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Employee other = (Employee) obj;
		if (awardedRe != other.awardedRe)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (pendingRe != other.pendingRe)
			return false;
		if (position != other.position)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", position=" + position
				+ ", username=" + username + ", awardedRe=" + awardedRe + ", pendingRe=" + pendingRe + "]";
	}
	
	
}

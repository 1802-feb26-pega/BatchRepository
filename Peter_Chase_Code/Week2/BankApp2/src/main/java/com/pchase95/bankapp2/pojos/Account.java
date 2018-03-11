package com.pchase95.bankapp2.pojos;

public class Account {
	private long id;
	private String name;
	private String email;
	private String password;
	private double balance;
	

	public Account() { }
	
	public Account(String name, String email, String password, double balance) {
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", email=" + email + ", balance=" + balance + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public long getId() { return id;}
	public String getName() { return name; }
	public String getEmail() { return email; }
	public double getBalance() { return balance; }
	public String getPassword() { return password; }
	
	public void setId(long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setBalance(double balance) { this.balance = balance; }
	public void setPassword(String password) { this.password = password; }


}

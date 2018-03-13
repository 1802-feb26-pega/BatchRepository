package com.pchase95.bankapp2.pojos;

public class Account {
	private long id;
	private long ownerId;
	private String name;
	private String pin;
	private double balance;
	
	public Account() {
		this.balance = 0.0;
		this.id = 0L;
	}
	
	public Account(long ownerId, String name, String pin) {
		this();
		this.ownerId = ownerId;
		this.name = name;
		this.pin = pin;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", ownerId=" + ownerId + ", name=" + name + ", pin=" + pin + ", balance=" + balance
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ownerId != other.ownerId)
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		return true;
	}

	public long getId() { return id; }
	public long getOwnerId() { return ownerId; }
	public String getName() { return name; }
	public String getPin() { return pin; }
	public double getBalance() { return balance; }

	public void setId(long id) { this.id = id; }
	public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
	public void setName(String name) { this.name = name; }
	public void setPin(String pin) { this.pin = pin; }
	public void setBalance(double balance) { this.balance = balance; }
}


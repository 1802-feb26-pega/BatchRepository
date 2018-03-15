package com.trms.pojos;

public interface User {
	public String getWholeName();
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public int getId();
	public void setId(int id);
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
}

package com.trms.pojos;

public interface User {
	public String getWholeName();
	public int getId();
	public void setId(int id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getDepartment();
	public void setDepartment(String dept);
	public double getPaidReimbursments();
	public void setPaidReimbursments(double paidReimbursments);
	
	public int getSupervisorId();
	public void setSupervisorId(int supervisorId);
	public int getDepHeadId();
	public void setDepHeadId(int depHeadId);
	public int getBenCoId();
	public void setBenCoId(int benCoId);
	public String getJobTitle();
	public void setJobTitle(String jobTitle);
}

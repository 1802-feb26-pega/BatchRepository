package com.bank.pojos;

public class Event {
	
	private int id;
	private int EmployeeId;
	private String type;
	private String format;
	private int cost;
	private String date;
	private String time;
	private String location;
	private String description;
	private String grade;
	private String approval;
	
	public Event() {}

	public Event(int EmployeeId, String type, String format, int cost, String date, 
			String time, String location, String description) {
		
		this.EmployeeId = EmployeeId;
		this.type = type;
		this.format = format;
		this.cost = cost;
		this.date = date;
		this.time = time;
		this.location = location;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
	
}

package com.trms.pojos;

public class Department {
	private int departmentId;
	private String departmentName;
	private int departmentHeadId;
	
	public Department() { }
	
	public Department(int departmentId, String departmentName, int departmentHeadId) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentHeadId = departmentHeadId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getDepartmentHeadId() {
		return departmentHeadId;
	}
	public void setDepartmentHeadId(int departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", departmentHeadId="
				+ departmentHeadId + "]";
	}
}

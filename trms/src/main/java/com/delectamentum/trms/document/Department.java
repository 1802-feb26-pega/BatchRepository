package com.delectamentum.trms.document;

public class Department {
	private int id,
				departmentHeadId;
	private String name;
	
	public Department() {}

	public Department(int id, int departmentHeadId, String name) {
		super();
		this.id = id;
		this.departmentHeadId = departmentHeadId;
		this.name = name;
	}

	public int getDepartmentHeadId() {
		return departmentHeadId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setDepartmentHeadId(int departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentHeadId=" + departmentHeadId + ", name=" + name + "]";
	}
	
}

package com.trms.dao;

import java.util.List;

import com.trms.pojos.Department;

public interface DepartmentDAO {
	public List<Department> getAllDepartments();
	public List<String> getAllDepartmentNames();
	public String getDepartmentNameById(int deptId);
	public int getDepartmentIdByName(String deptName);
}

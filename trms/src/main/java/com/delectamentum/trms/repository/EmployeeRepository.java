package com.delectamentum.trms.repository;

import java.util.List;

import com.delectamentum.trms.document.Employee;

public interface EmployeeRepository extends CRUDRepository<Employee>{

	public Employee getByEmail(String email);
	public Employee getByName(String firstname, String lastname);
	public List<Employee> getAllStandardEmployees();
	public List<Employee> getAllSupervisors();
	public List<Employee> getAllDepartmentHeads();
	public List<Employee> getAllBenCos();
	public List<Employee> getAllForOneDepartment(int departmentId);
	public List<Employee> getAllUnderSupervisor(int supervisorId);
}

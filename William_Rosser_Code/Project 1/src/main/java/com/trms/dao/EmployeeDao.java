package com.trms.dao;

import com.trms.pojos.*;


public interface EmployeeDao {
	public User getEmployee(String username);
	public User getEmployeeById(int id);
	public User addBenCo(BenCo bc);
	public User addDepHead(DepartmentHead dh);
	public User addDirectSupervisor(DirectSupervisor ds);
	public User addEmployee(Employee e);
}

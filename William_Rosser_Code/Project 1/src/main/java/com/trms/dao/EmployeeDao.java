package com.trms.dao;

import com.trms.pojos.User;

public interface EmployeeDao {
	public User getEmployee(String username);
	public User getEmployeeById(int id);
	public User addUser(User u);
}

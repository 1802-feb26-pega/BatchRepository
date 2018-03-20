package com.trms.dao;

import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;

public interface EmployeeDAO {
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByUsername(String username);
	public ArrayList<Event> getEventsByEmployee(Employee employee);
}

package com.proj.services;

import java.util.ArrayList;

import com.proj.pojos.Employee;

//Sets the contract for the EmployeeServices
/*Methods includes:
 * ValidateEmployess checks to see if employee exists in the database
 * getEmployees grabs all the employees  username in database
 * 
 * */
public interface EmployeeInterfaces{
	public Employee validateEmployee(String user, String password);
	public ArrayList<Employee> getEmployees();
}

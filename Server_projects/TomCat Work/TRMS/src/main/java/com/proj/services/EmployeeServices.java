package com.proj.services;

import java.util.ArrayList;
import com.proj.pojos.Employee;

public class EmployeeServices implements EmployeeInterfaces {
	public static DatabaseDao dao = new DatabaseDao();
	
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	static{
		employees.add(new Employee("Philip","Harris"));
	}
	
//-------------------------------------------------------------------------------
	
	public Employee validateEmployee(String emp, String password) {
		// TODO Auto-generated method stub
		for(Employee e: dao.getEmployeesUsername()){
			if(e.getUsername().equals(emp) & e.getPassword().equals(password)){
				dao.getEmployeeLogin(e.getUsername());
			}
		}
		return null;
	}

	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}
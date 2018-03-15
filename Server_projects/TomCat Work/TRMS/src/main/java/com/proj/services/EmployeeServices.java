package com.proj.services;

import java.util.ArrayList;
import com.proj.pojos.Employee;


/*
 * Employee services is a class that handles majority if no all
 * of the employess transactions
 */
public class EmployeeServices implements EmployeeInterfaces {
	public static DatabaseDao dao = new DatabaseDao();
	public Employee employee;
	
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	static{
		employees.add(new Employee("Philip","Harris"));
	}
	
//-------------------------------------------------------------------------------
	
	//RETURNS an EMPLOYEE if and only if they are in the database
	public Employee validateEmployee(String usrname, String password) {
		// TODO Auto-generated method stub
			
		employee = dao.getEmployeeLogin(usrname, password);
		System.out.println(employee.toString());
		if(employee.getKey() == 0){
			return null;
		}
		else if(employee.getUsername().equals(usrname) & 
				employee.getPassword().equals(password)){	
			return employee;
		}
		else 
			return null;
	}

	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
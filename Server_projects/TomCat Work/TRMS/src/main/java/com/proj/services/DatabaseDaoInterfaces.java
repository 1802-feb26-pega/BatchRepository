package com.proj.services;

import java.util.ArrayList;

import com.proj.pojos.Employee;
//Sets the contract for the Dao
/*Methods includes:
* getEmpolyeeLogin grabs employee info at login
* getEmployees grabs list of employees
* */
public interface DatabaseDaoInterfaces {
		public Employee getEmployeeLogin(String name,String password);
		public ArrayList<Employee> getEmployeesUsername();
}

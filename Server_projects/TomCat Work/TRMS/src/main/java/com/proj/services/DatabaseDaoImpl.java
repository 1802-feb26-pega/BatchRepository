package com.proj.services;

import java.util.ArrayList;

import com.proj.pojos.Employee;
//Sets the contract for the Dao
/*Methods includes:
* getEmpolyeeLogin grabs employee info at login
* getEmployees grabs list of employees
* */
public interface DatabaseDaoImpl {
		public Employee getEmployeeLogin(String s);
		public ArrayList<Employee> getEmployeesUsername();
}

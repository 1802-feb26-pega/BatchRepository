package com.pchase95.project1.driver;

import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.pojos.Employee;

public class TesterMain {
	public static void main(String[] args) {
		
		DAO<Employee> depDAO = new EmployeeDAO();
		
		for (Employee d : depDAO.getAll())
			System.out.println(d);
	}
}

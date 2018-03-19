package com.revature.trms.test;

import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.pojos.Employee;

public class MainTest {

	public static void main(String[] args) {
		
		EmployeeDAOImpl dao = new EmployeeDAOImpl();
		
		Employee e = dao.getEmployeeByEmail("joseph.k.moses6@gmail.com");
		
		System.out.println(e);
	}
	
}

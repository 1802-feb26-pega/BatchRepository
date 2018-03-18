package com.trms.service;

import com.trms.dao.EmployeeDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.pojos.Employee;

public class Service {
	
	public static void main(String[] args) {
	
		EmployeeDAO eDao = new EmployeeDAOImpl();
		Employee test = new Employee();
		String firstname = "Livy";
		String lastname = "Thom";
		String email = "lthom1@blog.com";
		String pass = "sFfsfESzs";
		int supervisor_id = 15;
		int department_id = 2;
		int level_id = 1;
		double available_reimbursement = 1000D;
		double pending_reimbursement = 0D;
		double awarded_reimbursement = 0D;
		
		test.setFirstname(firstname);
		test.setLastname(lastname);
		test.setEmail(email);
		test.setPass(pass);
		test.setSupervisorId(supervisor_id);
		test.setDepartmentId(department_id);
		test.setLevelId(level_id);
		test.setAvailableReimbursement(available_reimbursement);
		test.setPendingReimbursement(pending_reimbursement);
		test.setAwardedReimbursement(awarded_reimbursement);
		System.out.println(test);
		test = eDao.addEmployee(test);
		System.out.println(eDao.employeeExists(email));
		System.out.println(eDao.isValidPassword(email, "sFfsfESzs"));
		
//		System.out.println(eDao.getEmployeeById(3));
		
	}
	
	

}

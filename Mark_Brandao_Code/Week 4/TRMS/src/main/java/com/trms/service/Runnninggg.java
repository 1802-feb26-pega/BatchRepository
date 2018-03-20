package com.trms.service;

import org.mindrot.jbcrypt.BCrypt;

import com.trms.dao.EmployeeDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.pojos.Employee;

public class Runnninggg {
	public static void main(String[] args) {
		EmployeeDAO edao = new EmployeeDAOImpl();
		String pass = "qAD2r7JjKM";
		// Hash the password!
		String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
		Employee emp = edao.getEmployeeById(25);
		emp.setPass(hashed);
		edao.updateEmployee(emp);
		System.out.println("Success");
	}
}


//ecrampin4@icio.us
//qAD2r7JjKM
package com.project1.test;

import com.project1.dao.DAO;
import com.project1.dao.DAOImplement;
import com.project1.pojos.Address;
import com.project1.pojos.Employee;

public class test {
	static public void main(String[] args) {
		
		DAO dao = new DAOImplement();
		
		Employee emp = new Employee();
		Address a = new Address();
		
		a.setAddressId(999);
		a.setStreet("street");
		a.setCity("city");
		a.setStateName("state");
		a.setZip(123456);
		
		System.out.println(a.getCity());
		//dao.addEmployee(titleId, departmentId, firstName, lastName, addressId, email, pwd, reportsTo)
		
		//dao.addAddress(a.getStreet(), a.getCity(), a.getStateName(), a.getZip());
		
		//dao.addEmployee(1, 1, "firstName", "lastName", 999, "email", "pwd", 1);
		
	}
}

package com.delectamentum.trms.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.repository.CRUDRepository;
import com.delectamentum.trms.repository.EmployeeRepository;
import com.delectamentum.trms.repository.EmployeeRepositoryImplSQL;

public class EmployeeService {

	private static EmployeeRepository empRepo = new EmployeeRepositoryImplSQL();
	
	public Employee getEmployeeByEmail(String email) {
		return empRepo.getByEmail(email);
	}
	
	public Employee getEmployeeByName(String first, String last) {
		return empRepo.getByName(first, last);
	}
	
	public Employee getEmployeeById(int id) {
		return empRepo.getById(id);
	}
	
	public Employee getSupervisor(Employee e) {
		return empRepo.getById(e.getSupervisorid());
	}
	
	public Employee registerEmployee(Employee e) {
		if(getEmployeeById(e.getId()) == null) {
			return empRepo.save(e);
		}
		else
			return null;
	}
	
	public Employee updateEmployee(Employee e) {
		if(getEmployeeById(e.getId()) != null)
			return empRepo.update(e);
		else 
			return null;
	}

	public Employee getEmployeeByEmailAndPW(String email, String pw) {
		Employee target = getEmployeeByEmail(email);
		if(target == null)
			return null;
		
		if(BCrypt.checkpw(pw, target.getPassword()))
			return target;
		else
			return null;
	}
}

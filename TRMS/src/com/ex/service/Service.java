package com.ex.service;

import com.reimb.dao.DAO;
import com.reimb.dao.DAOImpl;
import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;
import java.sql.Date;
import java.util.ArrayList;

public class Service {
	
	static DAO dao = new DAOImpl();
	//static MessageDAO mdao = new MDAOImp();
	
	public static Employee login(String username, String password) {
		Employee emp = dao.getEmployee(username);
		if(emp == null) return null;
		else if (emp.getPassword().equals(password)) {
			return emp;
		}
		else return null;
	}
	
	public Employee addEmployee(Employee e) {
		return dao.addEmployee(e.getFirstName(), e.getLastName(), e.getEmail(), e.getPassword(),
				e.getDepartment(), e.getDOB(), e.getAddress(), e.getCity(), e.getState());
	}
	
	public Reimbursement createReimbursement(Reimbursement reimb) {
		return dao.addReimbursement(reimb.getEmpId(), reimb.getEventDate(), reimb.getCity(), reimb.getState(),
				reimb.getCost(), reimb.getDescription(), reimb.getFormatId(), reimb.getEventType(),
				reimb.getAttachId(), reimb.getApprovalId(), reimb.getHoursMissed());
	}
	
	public Boolean emailExists(String email) {
		Employee u = dao.getEmployee(email);
		if(u == null) return false;
		else return true;
	}
	
	public ArrayList<Reimbursement> getRequests(Employee emp){
		return dao.getReimbursementsById(emp.getEmpId());
	}
}

package com.revature.trms.service;

import java.util.List;

import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.dao.EventTypeLookUpDAOImpl;
import com.revature.trms.dao.GradingFormatLoopUpDAOImpl;
import com.revature.trms.dao.PriorityLookUpDAOImpl;
import com.revature.trms.dao.ReimbursementRequestDAOImpl;
import com.revature.trms.dao.StatusLookUpDAOImpl;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.GradingFormat;
import com.revature.trms.pojos.Priority;
import com.revature.trms.pojos.ReimbursementRequest;
import com.revature.trms.pojos.Status;
import com.revature.trms.pojos.TypeOfEvent;

public class Service {

	static EmployeeDAOImpl empDao = new EmployeeDAOImpl();
	static ReimbursementRequestDAOImpl rrDao = new ReimbursementRequestDAOImpl();
	static EventTypeLookUpDAOImpl eventDao = new EventTypeLookUpDAOImpl();
	static GradingFormatLoopUpDAOImpl gradingFormatDao = new GradingFormatLoopUpDAOImpl();
	static PriorityLookUpDAOImpl priorityDao = new PriorityLookUpDAOImpl();
	static StatusLookUpDAOImpl statusDao = new StatusLookUpDAOImpl();
	
	public Employee login(String email, String password) {
		Employee emp = empDao.getEmployeeByEmail(email);
		if(emp == null) {
			return null;
		}
		else if(emp.getPassword().equals(password)) {
			return emp;
		}
		else {
			return null;
		}
	}
	
	public boolean emailExists(String email) {
		Employee emp = empDao.getEmployeeByEmail(email);
		if(emp == null) return false;
		else return true;
	}
	
	public boolean supervisorExists(String fName, String lName) {
		Employee emp = empDao.getSupervisorByName(fName, lName);
		if(emp == null) return false;
		else return true;
	}
	
	public int getSupervisorId(String fName, String lName) {
		Employee supervisor = empDao.getSupervisorByName(fName, lName);
		return supervisor.getEmpId();
	}
	
	public Employee addEmployee(Employee newEmp) {
		return empDao.addEmployee(newEmp);
	}
	
	public ReimbursementRequest addReimbursmentRequest(ReimbursementRequest request) {
		System.out.println("in service add request");
		return rrDao.addReimbursementRequest(request);
	}
	
	public Employee updateEmployee(Employee updatedEmp) {
		System.out.println("in service update employee");
		return empDao.updateEmployee(updatedEmp);
	}
	
	public List<ReimbursementRequest> getAllPendingRequests(Employee emp){
		
		return rrDao.getAllPendingRequests(emp);
	}
	
	public List<ReimbursementRequest> getAllRequestsForReviewBySuper(Employee emp){
		return rrDao.getAllRequestsForReviewBySuper(emp);
	}
	
	public GradingFormat getGradingFormatById(int id) {
		return gradingFormatDao.getGradingFormat(id);
	}
	
	public TypeOfEvent getTypeOfEventById(int id) {
		return eventDao.getEventType(id);
	}
	
	public Priority getPriorityById(int id) {
		return priorityDao.getPriority(id);
	}
	
	public Status getStatusById(int id) {
		return statusDao.getStatus(id);
	}
	
	
}

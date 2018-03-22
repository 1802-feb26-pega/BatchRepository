package com.ex.service;

import java.util.ArrayList;

import com.project1.dao.DAO;
import com.project1.dao.DAOImplement;
import com.project1.pojos.Employee;
import com.project1.pojos.Request;
import com.project1.pojos.Requests;
import com.project1.pojos.EventDetails;

public class Service {
	
	static DAO dao = new DAOImplement();
	
	public Employee login(String email, String password) {
		Employee employee = dao.getEmployeeByEmail(email); // this is where we get from the database
		if(employee == null) return null;
		else if (employee.getPwd().equals(password)) {
			return employee;
		}
		else return null;
	}
	
	public Employee addEmployee(Employee e) {
		return dao.addEmployee(e.getTitleId(),e.getDepartmentId(),e.getFirstName(),
				e.getLastName(),e.getEmail(),e.getPwd(),e.getReportsTo());
	}
	
	
	public boolean emailExists(String email) {
		Employee e = dao.getEmployeeByEmail(email);
		if(e == null) return false;
		else return true;
	}
	
	public EventDetails addEventDetails(EventDetails e) {
		return dao.addEventDetails(e.getRequestId(), e.getEventId(), e.getEventDate(), e.getEventTime(), e.getEventCost(), e.getEventDescription(), e.getJustification(), e.getDaysMissed());
	}
	
	public Request addRequest(Request r) {
		return dao.addRequest(r.getEmployeeId(), r.getRequestTime());
	}
	/*
	 * public Requests addRequests(int aa, String bb, String cc, String dd, String ee,
			String ff, Double gg, String hh, String ii, int jj, String kk) {
		Requests rr = new Requests();
		System.out.println("in addRequests in Service.java");
		rr.setEmployeeId(1);
		return dao.addRequests(1, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk);
	 * */
	public Requests addRequestss(Requests r) {
		System.out.println("in addRequests in Service.java");
		r.setEmployeeId(1);
		return dao.addRequests(
				1,"pending", "Class", "default", "2018-03-15", "123 street, city, state, 123456", 100.00, "Event Description", "Justification", 1, "none");
				//(int)r.getEmployeeId(), 
				//(String)r.getRequestsStatus(), 
				//(String)r.getRequestsType(), 
				//(String)r.getRequestsGradingFormat(),
				//(String)r.getRequestsDate(), 
				//(String)r.getRequestsLocation(), 
				//(double)r.getRequestsCost(), 
				//(String)r.getRequestsDescription(), 
				//(String)r.getRequestsJustification(),
				//(int)r.getRequestsDaysMissed(), 
				//(String)r.getRequestsAttachments());
				
	}
	
	public ArrayList<Requests> getAllRequests(Employee e) {
		return dao.getRequests(e.getEmployeeId());
	}
	public static void main(String[] args) {
		dao.addRequests(1,"pending", "Class", "default", "2018-03-15", "123 street, city, state, 123456", 100.00, "Event Description", "Justification", 1, "none");
	}
}

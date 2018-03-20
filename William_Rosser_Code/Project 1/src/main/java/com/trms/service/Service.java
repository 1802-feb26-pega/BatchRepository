package com.trms.service;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.dao.EmployeeDao;
import com.trms.dao.EmployeeDaoImpl;
import com.trms.dao.ReimbursementDao;
import com.trms.pojos.BenCo;
import com.trms.pojos.DepartmentHead;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.Employee;
import com.trms.pojos.TRForm;
import com.trms.pojos.User;

public class Service {

	static EmployeeDao eDao = EmployeeDaoImpl.getInstance();
	static ReimbursementDao rDao = ReimbursementDao.getInstance();
	static ObjectMapper mapper = new ObjectMapper();	

	public static User login(String username, String password) {
		System.out.println(username + " service username");
		User user = eDao.getEmployee(username);
		if (user == null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		} else return null;
	}

	public static User addUser(User u, int type) {
		switch(type) {
		case 0: eDao.addEmployee((Employee) u); break;
		case 1: eDao.addDirectSupervisor((DirectSupervisor) u); break;
		case 2: eDao.addDepHead((DepartmentHead) u); break;
		case 3: eDao.addBenCo((BenCo) u); break;
		}
		return u;
	}
	
	public static boolean validateUsername(String username) {
		User u = eDao.getEmployee(username);
		System.out.println("eDao for validateUsername returned " + u);
		System.out.println("returning " + (u==null));
		return u == null;
	}
	
	public static String userToJSON(User u) throws IOException {
		String json = mapper.writeValueAsString(u);
		int roll = 0;
		if (u instanceof DirectSupervisor) roll = 1;
		else if (u instanceof DepartmentHead) roll = 2;
		else if (u instanceof BenCo) roll = 3;
		json = json.substring(0, json.length()-1) + ", \"type\":"+roll+"}";
		return json;
	}
	
	public static boolean addRequest(TRForm trf, User u) {
		trf = rDao.addRequest(trf, u);
		return trf != null;
	}
	
	public static String getRequestsFromUser(User u) throws JsonProcessingException {
		ArrayList<TRForm> trfarr = rDao.getTRFFromUser(u);
		String json = mapper.writeValueAsString(trfarr);
		System.out.println(json);
		return json;
	}
	
	public static String getRequestsForApprover(User u, int roll) throws JsonProcessingException {
		ArrayList<TRForm> trfarr = rDao.getTRFForApprover(u, roll);
		String json = mapper.writeValueAsString(trfarr);
		System.out.println(json);
		return json;
	}
}

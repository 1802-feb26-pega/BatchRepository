package com.ex.test;

import java.sql.Timestamp;
import java.sql.Date;

import com.trms.dao.DAO;
import com.trms.dao.DAOImpl;
import com.trms.pojos.Request;
import com.trms.pojos.User;

public class MainMain {
	static DAO dao = new DAOImpl();
	public static void main(String[] args)
	{
//		User u = dao.getUserByUsername("ck");
//		System.out.println(u.toString());
		
//		int uId = 2;
//		Request r =  new Request(2,"asdf","2018-10-10",
//				"2018-10-24","asdf","asdf",50,2,70,
//				"2018-3-18","2018-3-18 12:15:00",0,0);
//		
//		dao.addRequest(r.getEventType(), r.getStartDate(), r.getEndDate(), r.getLocation(),
//				r.getDescription(),r.getCost(),r.getGradingStyleId(), 
//				r.getGrade(), r.getRequestDate(),r.getRequestTime(), 
//				r.getFlaggedId(), r.getApprovalId(), uId);
		
		
//		dao.addUser("john", "smith", "js", "iamjohn", "unknown", "unknown", "supervisor", 1, 0, 0);
//		User u = dao.getUserByUsername("js");
//		System.out.println(u.toString());
		
		//dao.addUser("fred", "meyer", "fm", "iamfred", "unknown", "unknown", "department head", 1, 0, 0);
		//dao.addUser("keith", "richards", "kr", "iamkeith", "unknown", "unknown", "benefits coordinator", 1, 0, 0);
		
		//dao.addApproval(6);
		//dao.updateApproval(dao.getUserByUsername("js").getId(),"approved",6);
		//dao.updateApproval(dao.getUserByUsername("fm").getId(),"approved",6);
		//dao.updateApproval(dao.getUserByUsername("kr").getId(),"approved",6);
		User u = dao.getUserByUsername("ck");
		System.out.println(u.getPassword());
	}
}

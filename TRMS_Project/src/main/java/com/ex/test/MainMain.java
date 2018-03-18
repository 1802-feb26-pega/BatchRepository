package com.ex.test;

import java.sql.Timestamp;
import java.sql.Date;

import com.trms.dao.DAO;
import com.trms.dao.DAOImpl;
import com.trms.pojos.Request;
import com.trms.pojos.User;

public class MainMain {
	public static void main(String[] args)
	{
		DAO dao = new DAOImpl();
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
		
	}
}

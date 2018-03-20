package com.revature.trms.test;

import java.sql.Date;
import java.util.ArrayList;

import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.dao.ReimbursementRequestDAOImpl;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;

public class MainTest {

	public static void main(String[] args) {
		
		EmployeeDAOImpl edao = new EmployeeDAOImpl();
		
		Employee e = edao.getEmployeeByEmail("joseph.k.moses6@gmail.com");
		
		System.out.println(e.toString());
		
		ReimbursementRequestDAOImpl rrdao = new ReimbursementRequestDAOImpl();
		
		//ReimbursementRequest requestToAdd = new ReimbursementRequest(11, new Date(3/19/2018), new Date(3/20/2018), "here", "test2", 1, 100, 1, 70, "required", 1, 80, 1, 1);
		
		//System.out.println(rrdao.addReimbursementRequest(requestToAdd).toString());
		
		ArrayList<ReimbursementRequest> r = (ArrayList<ReimbursementRequest>) rrdao.getAllPendingRequests(e);
		for(ReimbursementRequest rr : r) {
			System.out.println(rr.toString());
		}
	}
	
}

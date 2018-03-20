package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;
import com.revature.trms.service.Service;

@WebServlet("/getAllPendingRequests")
public class GetAllPendingRequests extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = req.getSession();
		
		Employee emp = (Employee) session.getAttribute("emp");
		System.out.println(emp.getEmpId());
		Service service = new Service();
		
		ArrayList<ReimbursementRequest> pendingRequests = (ArrayList<ReimbursementRequest>) service.getAllPendingRequests(emp);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		for(ReimbursementRequest r : pendingRequests) {
			System.out.println(r);
		}
		
		out.write(mapper.writeValueAsString(pendingRequests));
		
}
}

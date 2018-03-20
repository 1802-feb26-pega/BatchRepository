package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;

@WebServlet("/request")
public class RequestServlet extends HttpServlet{
	
	static Service service = new Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("in requests -- get");
		
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("employee");
		
		ArrayList<Reimbursement> requests = service.getRequests(emp);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String reqString = mapper.writeValueAsString(requests);
		System.out.println(reqString);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(reqString);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("inside requests -- post");
		
		ObjectMapper mapper = new ObjectMapper();

		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		System.out.println(reimb.toString());
		reimb = service.createReimbursement(reimb);
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
	}
		
}

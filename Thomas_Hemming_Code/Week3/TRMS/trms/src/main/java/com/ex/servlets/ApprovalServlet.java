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

import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;
import com.trms.pojos.Employee;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/apprReimbursements")
public class ApprovalServlet extends HttpServlet{
	
	static Service service = new Service();

	//get events per employee
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN Reimbursments -- GET");
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		
		System.out.println(employee.toString());
		
		ArrayList<Reimbursement> reimbursements = service.getReimbursementsByPending();
		
		ObjectMapper mapper = new ObjectMapper();
		String reimbursementString = mapper.writeValueAsString(reimbursements);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(reimbursementString);
		

	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
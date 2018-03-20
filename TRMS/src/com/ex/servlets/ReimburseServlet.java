package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;

@WebServlet("/reimburse")
public class ReimburseServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();

		Reimbursement reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		System.out.println(reimb.toString());
		reimb = service.createReimbursement();
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
	}
		
}

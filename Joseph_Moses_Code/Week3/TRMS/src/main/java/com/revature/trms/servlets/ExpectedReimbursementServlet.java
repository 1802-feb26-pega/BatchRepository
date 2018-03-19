package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.dao.EventTypeLookUpDAOImpl;

@WebServlet("/expectedReimbursment")
public class ExpectedReimbursementServlet extends HttpServlet {

	EventTypeLookUpDAOImpl dao = new EventTypeLookUpDAOImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String[] input = mapper.readValue(req.getInputStream(), String[].class);
		int eventTypeId= Integer.parseInt(input[0]);
		double cost = Double.parseDouble(input[1]);
		Integer coverage = dao.getCoverageById(eventTypeId);
		double coverageDecimal = coverage/100.0;
		int expectedReimbursment = (int) (cost * coverageDecimal);
		System.out.println(coverage);
		System.out.println(expectedReimbursment);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(expectedReimbursment));
		
		
	}
}

package com.proj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.services.ClaimServices;

@WebServlet("/checkforms")
public class LoadCheckingFormsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("Employee");
		
		ClaimServices service = new ClaimServices();		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");		
		String empJSON = mapper.writeValueAsString(service.getClaims(emp));
		out.write(empJSON);				
	}
}

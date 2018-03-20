package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.pojos.Employee;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = mapper.readValue(req.getInputStream(), Employee.class);
		
		emp = service.addUser(emp);
		
		HttpSession session = req.getSession();
		session.setAttribute("user", emp);
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
		
	}

}

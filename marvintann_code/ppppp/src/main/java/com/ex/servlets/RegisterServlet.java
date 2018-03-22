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
import com.project1.pojos.Employee;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();
		Employee e = mapper.readValue(req.getInputStream(), Employee.class);
		
		System.out.println(e.toString());
		e = service.addEmployee(e);
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
		
	}

}

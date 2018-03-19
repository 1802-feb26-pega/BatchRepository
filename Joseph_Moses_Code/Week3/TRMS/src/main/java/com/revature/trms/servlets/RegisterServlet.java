package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.service.Service;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();
		Employee newEmp = mapper.readValue(req.getInputStream(), Employee.class);
		
		System.out.println(newEmp.toString());
		newEmp = service.addEmployee(newEmp);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(newEmp));
		
	}
}

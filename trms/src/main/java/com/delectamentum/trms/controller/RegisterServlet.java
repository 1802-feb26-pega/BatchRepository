package com.delectamentum.trms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static EmployeeService esv = new EmployeeService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		
		RegisterRequest rr = mapper.readValue(req.getInputStream(), RegisterRequest.class);
		System.out.println(rr);
		
		Employee supervisor = esv.getEmployeeByName(rr.getSupervisorf(), rr.getSupervisorl());
		System.out.println(supervisor);
		
		Employee u = new Employee();
		u.setEmail(rr.getEmail());
		u.setFirstname(rr.getFirstname());
		u.setLastname(rr.getLastname());
		u.setPassword(BCrypt.hashpw(rr.getPassword(), BCrypt.gensalt()));
		u.setSupervisorid(supervisor.getId());
		u.setDepartment(rr.getDepartmentId());
		
		System.out.println(u);

		u = new EmployeeService().registerEmployee(u);
		
		if(u != null) {
			String json = mapper.writeValueAsString(u);
			out.write(json);
		} else {
			out.write("null");
		}
	}
}

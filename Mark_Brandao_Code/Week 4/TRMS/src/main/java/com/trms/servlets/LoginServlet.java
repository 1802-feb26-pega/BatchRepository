package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.Employee;
import com.trms.service.Service;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. Get request body from requets object
		ObjectMapper mapper = new ObjectMapper();
		String[] employeeInfo = mapper.readValue(req.getInputStream(), String[].class);
		String email = employeeInfo[0];
		String password = employeeInfo[1];
		
		Employee employee = service.login(email, password);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		if(employee != null) {
			String employeeJSON = mapper.writeValueAsString(employee);
			
			HttpSession session = req.getSession();
			session.setAttribute("employee", employee);
			
			out.write(employeeJSON);
		} else {
			out.write("null");
		}
		
	}
}

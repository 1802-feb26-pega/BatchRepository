package com.proj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.pojos.Employee;
import com.proj.services.EmployeeServices;

@WebServlet("/super")
public class SuperServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException{
		
			EmployeeServices eService  = new EmployeeServices();
			HttpSession session = req.getSession();
			Employee emp = (Employee) session.getAttribute("Employee");
			
			ObjectMapper mapper = new ObjectMapper();
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");	
			System.out.println("Grabbing the super and creating JSON");
			String empJSON = mapper.writeValueAsString(eService.getSuper(emp));
			System.out.println("JSON is SENT");
			out.write(empJSON);
			
			
	}
}

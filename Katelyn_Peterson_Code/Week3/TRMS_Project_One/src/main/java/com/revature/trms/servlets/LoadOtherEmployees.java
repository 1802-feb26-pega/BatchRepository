package com.revature.trms.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.service.EmployeeService;

@WebServlet("/otherEmps")
public class LoadOtherEmployees extends HttpServlet
{
	private static EmployeeService eService = new EmployeeService();
	private Employee other;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Employee other;
		//System.out.println("Retrieving other employees!");
		
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		//System.out.println(json);

		//2. instantiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		
		//3. Convert received JSON string into appropriate object
		Integer empInfo = mapper.readValue(json, Integer.class);
		//Integer otherEmpId = Integer.getInteger(empInfo);
		
		other = eService.getEmployeeById(empInfo);
		
		
		mapper = new ObjectMapper();
		String otherEmployee = mapper.writeValueAsString(other);
		//System.out.println(otherEmployee);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		//out.write(otherEmployee);
		
		
		//String userJSON = mapper.writeValueAsString(emp);
		//System.out.println(userJSON);
		HttpSession session = req.getSession();
		session.setAttribute("temp", otherEmployee);
		//System.out.println(emp);
		out.write(otherEmployee);
		
	}
}

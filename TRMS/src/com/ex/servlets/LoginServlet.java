package com.ex.servlets;

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

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimb.pojos.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		System.out.println("in login servlet");
		
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		System.out.println("Inserted value is: " + json);
		
		//2. Instantiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		
		//3. Convert recieved JSON string into appropriate object
		String[] empInfo = mapper.readValue(json, String[].class);
		String email = empInfo[0];
		String password = empInfo[1];
		
		Employee emp = Service.login(email, password);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		if(emp!=null) {
			String userJSON = mapper.writeValueAsString(emp);
		
			HttpSession session = req.getSession();
			session.setAttribute("employee",  emp);
			
			System.out.println("logging in as " + email);
			out.write(userJSON);
		}
		else {
			out.write("null"); //null as JSON string
		}
		
	}
}

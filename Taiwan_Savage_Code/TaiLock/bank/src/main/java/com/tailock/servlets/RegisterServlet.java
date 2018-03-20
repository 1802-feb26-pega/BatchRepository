package com.tailock.servlets;

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
import com.tailock.pojos.Employee;
import com.tailock.pojos.User;
import com.tailock.service.Service;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	static Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("In register servlet");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String[] empDetails = mapper.readValue(json, String[].class);
		
		String fn = empDetails[0];
		String ln = empDetails[1];
		String email = empDetails[2];
		String pw = empDetails[3];
		
		Employee employee = service.register(fn, ln, email, pw);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		if(employee!=null) {
			String userJSON = mapper.writeValueAsString(employee);
			HttpSession session = req.getSession();
			session.setAttribute("employee", employee);
			System.out.println("JSON: " + userJSON);
			out.write(userJSON); //write the user as a json object
		}
		else {
			out.write("null");
		}
	}
	
}

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
import com.tailock.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
		System.out.println("in login servlet");
		
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		System.out.println(json);

		//2. instantiate jackson mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		//3. Convert received JSON string into appropriate object
		
		String[] userInfo = mapper.readValue(json, String[].class); // read json string in and change to object
		String email = userInfo[0];
		String password = userInfo[1];
		
		Employee employee = service.login(email, password);
		
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
			out.write("null"); //null as json string
		}
		
		
	}

}

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

@WebServlet("/validate")
public class ValidationServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String username = mapper.readValue(req.getInputStream(),String.class);
		
		System.out.println("in validate " + username);
		
		Service service = new Service();
		boolean exists = service.usernameExists(username);
		
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(exists));
	}
}

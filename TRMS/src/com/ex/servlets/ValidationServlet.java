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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		System.out.println("in validate");
		
		ObjectMapper mapper = new ObjectMapper();
		String email = mapper.readValue(req.getInputStream(), String.class);
		
		Service service = new Service();
		boolean exists = service.emailExists(email);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		System.out.println("mapper version is: " + mapper.writeValueAsString(exists));
		out.write(mapper.writeValueAsString(exists));
	}
}

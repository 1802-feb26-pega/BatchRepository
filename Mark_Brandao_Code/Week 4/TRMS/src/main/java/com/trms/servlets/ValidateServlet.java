package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.service.Service;

@SuppressWarnings("serial")
@WebServlet("/validate")
public class ValidateServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
		ObjectMapper mapper = new ObjectMapper();
		String email = mapper.readValue(req.getInputStream(), String.class);
		Service service = new Service();
		
		boolean exists = service.emailExists(email);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(exists));
	};
}

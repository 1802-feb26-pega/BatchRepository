package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank.service.Login;

@SuppressWarnings("serial")
@WebServlet("/validate")
public class ValidationServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String username = mapper.readValue(req.getInputStream(), String.class);
		
		System.out.println(username);
		
		Login ll = new Login();
		boolean exists = ll.verifyUsername(username);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(mapper.writeValueAsString(exists));
	}
}

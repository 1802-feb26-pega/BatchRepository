package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebServlet("/hello")
public class HelloServlet extends GenericServlet {
	private static final long serialVersionUID = 365342781603822126L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/html");
		
		out.println("<center><h1>Hello from servlet bud</h1></center>");
	}
}

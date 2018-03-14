package com.ex.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//For reference:
//@WebServlet("/hello")
public class HelloServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) 
			throws ServletException, IOException {
		PrintWriter out =  res.getWriter();
		res.setContentType("text/html");
		out.println("<h1>Hello! Welcome to your first Servlet.</h1>");
	}

}

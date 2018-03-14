package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/real")
public class RealServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("Welcome to your second servlet!<br>");
		out.println(req.getParameter("name"));
		System.out.println(req.getParameter("name" ));
			
	}
	
	

}

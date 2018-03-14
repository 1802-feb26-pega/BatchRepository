package com.ex.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
		//System.out.println(req.getParameter("name"));
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String next = paramNames.nextElement();
			String val = req.getParameter(next);
			System.out.println(next + "=" + val);
			out.println(next + ": " + val + "<br><hr><br>");
			
		}
		out.println("<h3>About Our Request:</h3><br>"
				+req.getMethod() + " "
				+req.getServerName() + " "
				+req.getServerPort() + "<br><hr><br>");
		
		Enumeration<String> info = req.getHeaderNames();
		while (info.hasMoreElements()) {
			String n = info.nextElement();
			out.println("Name: " + n + "<br>Value: " + req.getHeader(n) + "<br><br>");
		}
		out.println("<hr><br>");
		
	}
}

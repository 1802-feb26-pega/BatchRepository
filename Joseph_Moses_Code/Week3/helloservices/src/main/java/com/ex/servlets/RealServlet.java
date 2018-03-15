package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/real")
public class RealServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("Welcome to your second servlet!<br>");
		
		//out.println(req.getParameter("name"));
		
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String param = paramNames.nextElement();
			String val = req.getParameter(param);
			out.println(param + " : " + val + "<br>");
		}
		
		out.println("<hr><h3>About our request...</h3><br>" +
					req.getMethod() + " " + req.getServerName() +
					" : " + req.getServerPort() + "<br>");
		
		Enumeration<String> info = req.getHeaderNames();
		while(info.hasMoreElements()){
			String name = info.nextElement();
			String value = req.getHeader(name);
			out.println(name + " : " + value + "<br>");
		}
	}

}

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
public class RealServlet extends HttpServlet {
	//access servlet at [servername]:[port]/[contextroot]/[urlpattern]
	//context root in maven projects will be artifact id
	//can check or change it at project properties -> web project settings -> context root
	//https://learn.onemonth.com/understanding-http-basics/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("Welcome to your second servlet!<br>");
//		out.println(req.getParameter("name"));
//		System.out.println(req.getParameter("name"));
		
		//sample query string:
		//http://localhost:8099/helloservlets/real?name=genesis&age=100&bio=this+is+my+bio
		Enumeration<String> paramNames = req.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String param = paramNames.nextElement();
			String val = req.getParameter(param);
			
			System.out.println(param + " : " + val);
			out.println(param + " : " + val + "<br>");
		}
		
		out.println("<hr><br><h3>About our request...</h3><br>" +
					req.getMethod() + " " + req.getServerName() +":" + req.getServerPort() + "<br>");
		
		Enumeration<String> info = req.getHeaderNames();
		while(info.hasMoreElements()) {
			String name = info.nextElement();
			String value = req.getHeader(name);
			
			System.out.println(name + " : " + value);
			out.println(name + " : " + value + "<br>");
			
		}
		
			
	}
	
	
	
	

}

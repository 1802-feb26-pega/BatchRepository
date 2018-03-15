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
	private static final long serialVersionUID = -7186060817331209188L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("<h2>Second servlet, good memes</h2>");
	
//		out.println(req.getParameter("name")");
		
		Enumeration<String> pairs = req.getParameterNames();
		while (pairs.hasMoreElements()) {
			String param = pairs.nextElement();
			String val = req.getParameter(param);
			out.println(param + " : " + val + "<br>");
		}
		
		out.println("<hr><br><h3>About our request...</h3><br>"
				  + req.getMethod() + " " + req.getServerName() + ":" + req.getServerPort() + "<br>");
		
		
		Enumeration<String> info = req.getHeaderNames();
		while (info.hasMoreElements()) {
			String name = info.nextElement();
			String value = req.getHeader(name);
			
			out.println(name + " : " + value + "<br>");
		}
		
	}
	
}

package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(process(req, resp));
		
		String page = process(req, resp);
		System.out.println(page);
		req.getRequestDispatcher(page).forward(req, resp);
		
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case("/JoshTRMS/loadlanding.view") :
			return "partials/login.html";
		case("/JoshTRMS/loadnav.view") :
			return "partials/navbar.html";
		case("/JoshTRMS/loadhome.view") :
			return "partials/home.html";
		case("/JoshTRMS/loadregister.view") :
			return "partials/register.html";
			
		
		}
		
		return null;
		
	}

}

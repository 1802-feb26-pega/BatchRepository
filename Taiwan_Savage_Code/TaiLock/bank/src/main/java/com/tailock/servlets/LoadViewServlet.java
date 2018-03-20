package com.tailock.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("In loadview");
		String page = process(req, resp);
		req.getRequestDispatcher(page).forward(req, resp);
		
		
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		
		switch(req.getRequestURI()) {
			
		case("/bank/loadlanding.view"):
			return "partials/login.html";
		
		case("/bank/loadnav.view"):
			return "partials/navbar.html";
		
		case("/bank/loadhome.view"):
			return "partials/Home.html";
		
		case("/bank/loadregister.view"):
			return "partials/register.html";
		
		case("/bank/loadrequests.view"):
			return "partials/requests.html";
		}
		
		return req.getRequestURI();
		
	}
}
package com.ex.servlets;

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
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		switch(req.getRequestURI()) {
		case("/trms/loadlanding.view") :
			return "partials/login.html";
		case("/trms/loadnav.view") :
			return "partials/navbar.html";
		case("/trms/loadhome.view") :
			return "partials/home.html";
		case("/trms/loadApprovals.view") :
			return "partials/approve.html";
		case("/trms/loadEmployeeEvents.view") :
			return "partials/events.html";
		case("/trms/loadEmployeeReimbursements.view") :
			return "partials/reimbursements.html";

			
		
		}
		
		return req.getRequestURI();
		
	}

}
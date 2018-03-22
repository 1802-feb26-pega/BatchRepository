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
		case("/ppppp/loadlanding.view") :
			return "partials/login.html";
		case("/ppppp/loadnav.view") :
			return "partials/navbar.html";
		case("/ppppp/loadhome.view") :
			return "partials/home.html";
		case("/ppppp/loadrequestlist.view") :
			return "partials/requestList.html";
		case("/ppppp/loadtest.view") :
			return "partials/test.html";
		case("/ppppp/loadrequestsform.view") :
			return "partials/request.html";
		}
		
		return req.getRequestURI();
		
	}

}
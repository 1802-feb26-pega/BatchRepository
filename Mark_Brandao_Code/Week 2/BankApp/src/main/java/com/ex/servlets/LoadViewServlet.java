package com.ex.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(process(req, res));
		
		String page = process(req, res);
		req.getRequestDispatcher(page).forward(req, res);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse res) {
		switch(req.getRequestURI()) {
		case("/bankapp/loadlanding.view"): 
			return "partials/login.html";
		case("/bankapp/loadnav.view"):
			return "partials/navbar.html";
		case("/bankapp/loadhome.view"):
			return "partials/home.html";
		case("/bankapp/loadregister.view"):
			return "partials/register.html";
		}
		return req.getRequestURI();
	}
}

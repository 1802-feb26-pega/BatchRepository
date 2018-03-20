package com.trms.servlets;

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
		req.getRequestDispatcher(page).forward(req,  res);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse res) {
		switch(req.getRequestURI()) {
		case("/trms/loadlanding.view"):
			return "partials/login.html";
		case("/trms/loadregister.view"):
			return "partials/register.html";
		case("/trms/loadnav.view"):
			return "partials/navbar.html";
		case("/trms/loadhome.view"):
			return "partials/home.html";
		case("/trms/loadreqform.view"):
			return "partials/reqform.html";
		}
		
		return req.getRequestURI();
	}
}

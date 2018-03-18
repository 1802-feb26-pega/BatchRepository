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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
			System.out.println("LoadView is trying to load " + process(req, resp));
			
			String page = process(req, resp);
			req.getRequestDispatcher(page).forward(req, resp);
			}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Looking for " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case("/TRMS/loadlanding.view"):
			System.out.println("loading landing view");
			return "partials/login.html";
		case("/TRMS/loadnav.view"):
			System.out.println("loading nav view");
			return "partials/navbar.html";
		case("/TRMS/loadhome.view"):
			System.out.println("loading home view");
			return "partials/home.html";
		case("/TRMS/loadregister.view"):
			System.out.println("loading register view");
			return "partials/register.html";
		}
		System.out.println("Skipped all of the cases somehow");
		return null;
	}
}
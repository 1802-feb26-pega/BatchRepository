package com.revature.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		System.out.println(process(req, resp));
		
		String page = process(req, resp);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp)
	{
		switch(req.getRequestURI())
		{
		case("/TRMS_Project_One/loadLogin.view"):
			return "partials/login.html";
		case("/TRMS_Project_One/loadNav.view"):
			return "partials/navbar.html";
		case("/TRMS_Project_One/loadHome.view"):
			return "partials/home.html";
		case("/TRMS_Project_One/loadNew.view"):
			return "partials/newRequest.html";
		}
		
		return req.getRequestURI();
	}
}

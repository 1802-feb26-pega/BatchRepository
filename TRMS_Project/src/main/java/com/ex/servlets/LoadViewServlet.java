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
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		System.out.println("in load view servlet");
		
		String page = process(req,res);
		req.getRequestDispatcher(page).forward(req,res);
	}//doGet
	
	static String process(HttpServletRequest req,HttpServletResponse res)
	{
		switch(req.getRequestURI())
		{
		case("/rev/loadlanding.view"):
			return "/partials/login.html";

		case("/rev/loadregister.view"):
			System.out.println("register");
			return"/partials/register.html";
/*			
		case("/bank/loadnav.view"):
			System.out.println("navbar");
			return "/partials/navbar.html";
			
		case("/bank/loadhome.view"):
			System.out.println("home");
			return"/partials/Home.html";
			
		
			
		case("/bank/loadupload.view"):
			System.out.println("asdfasdfasdfasdfasdf");
			return "/partials/upload.html";
*/		
		default:
			System.out.println("default");
			return "/index.html";
		}
		
		
	}//process
	
}//loadviewservlet

/*
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
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println(process(req,res));
		System.out.println("load view servlet");

		String page = process(req,res);
		req.getRequestDispatcher(page).forward(req, res);
	}

	//we do this front controller design pattern instead of having a bunch of servlets with @WebServlet("/blah") at the top because they all do the same thing
	static String process(HttpServletRequest req, HttpServletResponse res)
	{
		switch(req.getRequestURI())
		{
		case("/bank/loadlanding.view"):
			System.out.println("landing");
			return "/partials/login.html";
			
		case("/bank/loadnav.view"):
			System.out.println("navbar");
			return "/partials/navbar.html";
			
		case("/bank/loadhome.view"):
			System.out.println("home");
			return"/partials/Home.html";
			
		case("/bank/loadregister.view"):
			System.out.println("register");
			return"/partials/register.html";
			
		case("/bank/loadupload.view"):
			System.out.println("asdfasdfasdfasdfasdf");
			return "/partials/upload.html";
		
		default:
			System.out.println("default");
			return "index.html";
		}

		//return req.getRequestURI();
	}
}

 */



package com.proj.servlet;

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
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String page = process(req,resp);
		req.getRequestDispatcher(page).forward(req, resp);
	}

	static String process(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		switch(req.getRequestURI()){
		case "/TRMS/loadlanding.view": 
			return "partials/Login.html";
		case "/TRMS/loadhome.view":
			return "partials/Home.html";
		case "/TRMS/loadnav.view":
			return "partials/navbar.html";
		case "/TRMS/loadForm.view":
			return "partials/form.html";
		case "/TRMS/loadcheckForm.view":
			return "partials/FormInfo.html";
		}
		return req.getRequestURI();
	}
}

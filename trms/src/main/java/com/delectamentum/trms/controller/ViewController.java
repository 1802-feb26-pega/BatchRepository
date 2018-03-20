package com.delectamentum.trms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 4046479835052470002L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println(process(req, resp));
		String page = process(req, resp);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	private static String process(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/trms/landing.view":
			return "partials/login.html";
		case "/trms/nav.view":
			return "partials/navbar.html";
		case "/trms/register.view":
			return "partials/register_bs4.html";
		case "/trms/home.view":
			return "partials/personalrequests.html";
		case "/trms/appr_requests.view":
			return "partials/approvablerequests.html";
		case "/trms/request.view":
			return "partials/requestform.html";
		default:
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "/trms/oops.html";
		}
	}
}

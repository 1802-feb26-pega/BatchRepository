package com.pchase95.trms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")
public class LoadViewServlet extends HttpServlet {
	private static final long serialVersionUID = -7670839531119137689L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = process(req, resp);
		System.out.println(page);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		switch (req.getRequestURI()) {
		case "/trms/loadlanding.view":
			return "partials/login.html";
		case "/trms/loadnav.view":
			return "partials/navbar.html";
		case "/trms/loadregister.view":
			return "partials/register.html";
		case "/trms/loadhome.view":
			return "partials/home.html";
		default: break;
		}
		
		return req.getRequestURI();
	}
}

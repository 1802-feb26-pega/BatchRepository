package com.trms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.view")
public class ViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = process(req);
		//System.out.println("redirecting to " + page);
		try {
			req.getRequestDispatcher(page).forward(req, resp);
		} catch (Exception e) {
			System.err.println("Something fucked up. Page = " + page);
		}
	}

	static String process(HttpServletRequest req) {
		switch(req.getRequestURI()) {
		case("/TRMS/loadlogin.view"): 
			return "partials/login.html";
		case("/TRMS/loadnav.view") :
			return "partials/navbar.html";
		case("/TRMS/loadhome.view") :
			return "partials/home.html";
		case("/TRMS/loadregister.view") :
			return "partials/register.html";
		case("/TRMS/loadManageRequests.view"):
			return "partials/manage_requests.html";
		case("/TRMS/loadForm.view"):
			return "partials/RequestForm.html";
		//TODO: Other views
		}
		return req.getRequestURI();
	}
}

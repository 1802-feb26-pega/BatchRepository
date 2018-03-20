package com.pchase95.project1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -8373464578081182526L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in logout servlet");
		
		HttpSession session = req.getSession();
		if (session != null) {
			session.removeAttribute("employee");
			session.invalidate();
			System.out.println("Session invalidated");
		}
		resp.sendRedirect("index.html");
	}
}

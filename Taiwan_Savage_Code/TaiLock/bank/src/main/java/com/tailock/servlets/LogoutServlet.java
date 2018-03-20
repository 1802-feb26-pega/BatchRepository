package com.tailock.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getSession(false) == null){
			resp.sendRedirect("index.html");
		}
		
		HttpSession session = req.getSession(false);
		if(session != null){
			session.removeAttribute("user");
			session.invalidate();
		}
		resp.sendRedirect("index.html");
	}
}

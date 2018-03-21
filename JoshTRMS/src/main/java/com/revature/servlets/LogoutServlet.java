package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		System.out.println("in logout servlet");
		
		
		HttpSession session = req.getSession(false);
		if(session != null){
			session.removeAttribute("user");
			session.invalidate();
			System.out.println("Session invalidated!");
		}
		ServletContext context= getServletContext();
		RequestDispatcher rd= context.getRequestDispatcher("/index.html");
		rd.include(req, resp);
		
	
	}
}
package com.proj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(req.getSession(false) == null){
			//req.getSession returns the current HttpSession,  with no parameters
			//it creates one if none exists, so we add param of false 
			//bool value of true would create session
			
		}
		
		HttpSession session = req.getSession(false);
		if(session != null){
			session.removeAttribute("employee");
			session.invalidate();
		}
		 resp.sendRedirect("index.html");
		
		
		
		
	}
	
}

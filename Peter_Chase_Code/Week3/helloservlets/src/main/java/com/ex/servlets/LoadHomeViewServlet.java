package com.ex.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loadhome")
public class LoadHomeViewServlet extends HttpServlet {
	private static final long serialVersionUID = -263241626265086855L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In load home view servlet");
		
		req.getRequestDispatcher("partials/login.html").forward(req, resp);
	}
}
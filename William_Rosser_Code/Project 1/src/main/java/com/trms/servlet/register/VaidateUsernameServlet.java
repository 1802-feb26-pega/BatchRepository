package com.trms.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.service.Service;

@WebServlet("/validate_username")
public class VaidateUsernameServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String un = req.getParameter("username");
		System.out.println("Parameter = " + un);
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String respString = Service.validateUsername(un)? "true": "";
		System.out.println("respString = " + respString);
		out.print(respString);
	}
}

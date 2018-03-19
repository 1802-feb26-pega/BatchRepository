package com.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.pojos.User;
import com.trms.service.Service;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		HttpSession s = req.getSession(false);
		if (s==null) {
			out.write("null");
			return;
		}
		User u = (User) s.getAttribute("user");
		if (u==null) {
			out.write("null");
			return;
		}
		out.write(Service.userToJSON(u));
	}
	
	
}

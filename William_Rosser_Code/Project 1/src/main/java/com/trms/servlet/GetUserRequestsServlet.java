package com.trms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.pojos.User;
import com.trms.service.Service;

@WebServlet("/get_user_requests")
public class GetUserRequestsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = Service.getRequestsFromUser((User) req.getSession().getAttribute("user"));
		resp.setContentType("application/json");
		resp.getWriter().write(json);
	}
}

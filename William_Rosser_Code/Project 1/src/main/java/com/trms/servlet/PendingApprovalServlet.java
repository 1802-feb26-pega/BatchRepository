package com.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pending_approval")
public class PendingApprovalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO URGENT: Get requests pending approval and send back as JSON
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.write("none");
	}
}

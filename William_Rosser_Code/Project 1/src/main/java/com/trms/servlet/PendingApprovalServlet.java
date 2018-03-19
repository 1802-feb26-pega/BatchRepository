package com.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.pojos.DepartmentHead;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.User;
import com.trms.service.Service;

@WebServlet("/pending_approval")
public class PendingApprovalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO URGENT: Get requests pending approval and send back as JSON
		User u = (User) req.getSession().getAttribute("user");
		int type = 0;
		if (u instanceof DirectSupervisor) type=1;
		else if (u instanceof DepartmentHead ) type=2;
		else type = 3;
		String json = Service.getRequestsForApprover(u, type);
		System.out.println(json);
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.write(json);
	}
}

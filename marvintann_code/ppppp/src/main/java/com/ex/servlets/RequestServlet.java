package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.pojos.Request;
import com.project1.pojos.Requests;

@WebServlet("/request")
public class RequestServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doPost of RequestServlet");
		HttpSession session = req.getSession();
		Service service = new Service();
		System.out.println("session got");
		ObjectMapper mapper = new ObjectMapper();
		Requests r = mapper.readValue(req.getInputStream(), Requests.class);
		System.out.println("mapped");
		System.out.println(r.toString());
		System.out.println(r);
		System.out.println(r.getRequestsId());
		System.out.println(r.getEmployeeId());
		System.out.println(r.getRequestsStatus());
		System.out.println(r.getRequestsType());
		System.out.println(r.getRequestsGradingFormat());
		System.out.println(r.getRequestsDate());
		System.out.println(r.getRequestsLocation());
		System.out.println(r.getRequestsCost());
		System.out.println(r.getRequestsDescription());
		System.out.println(r.getRequestsJustification());
		System.out.println(r.getRequestsDaysMissed());
		System.out.println(r.getRequestsAttachments());
		r.setEmployeeId(1);
		r = service.addRequestss(r);
		System.out.println("database");
		PrintWriter print = resp.getWriter();
		System.out.println("printwriter");
		resp.setContentType("application/json");
		System.out.println("response");

	}


}

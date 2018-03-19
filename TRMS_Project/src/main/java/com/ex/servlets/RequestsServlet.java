package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.Request;
import com.trms.pojos.User;

@WebServlet("/requests")
public class RequestsServlet  extends HttpServlet{
	
	static Service service = new Service();

	//get accounts per user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("IN REQUESTS -- GET");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user"); // get logged user from session
		
		//System.out.println(user.toString());
		System.out.println(user.getUsername());
		
		List<Request> requests = service.getRequests(user);
		
		ObjectMapper mapper = new ObjectMapper();
		String requestString = mapper.writeValueAsString(requests);
		
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");
		out.write(requestString);
	}
	
	//create user account
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	//update account
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}


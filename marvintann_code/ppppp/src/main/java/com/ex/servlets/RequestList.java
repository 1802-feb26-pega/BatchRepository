package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.dao.DAO;
import com.project1.dao.DAOImplement;
import com.project1.pojos.Employee;
import com.project1.pojos.Request;
import com.project1.pojos.Requests;

@WebServlet("/requestList")
public class RequestList extends HttpServlet {
	static Service service = new Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN REQUESTS -- GET");
		HttpSession session = req.getSession();
		Employee user = (Employee) session.getAttribute("user"); // get logged user from session
		
		System.out.println(user.toString());
		
		ArrayList<Requests> requests = service.getAllRequests(user);
		
		ObjectMapper mapper = new ObjectMapper();
		String accString = mapper.writeValueAsString(requests);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(accString);
		

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

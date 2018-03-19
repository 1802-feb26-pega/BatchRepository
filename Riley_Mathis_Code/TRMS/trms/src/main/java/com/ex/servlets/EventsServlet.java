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

import com.trms.pojos.Event;
import com.trms.pojos.Employee;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/events")
public class EventsServlet extends HttpServlet{
	
	static Service service = new Service();

	//get accounts per user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN Events -- GET");
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee"); // get logged user from session
		
		System.out.println(employee.toString());
		
		ArrayList<Event> events = service.getEvents(employee);
		
		ObjectMapper mapper = new ObjectMapper();
		String eventString = mapper.writeValueAsString(events);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(eventString);
		

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
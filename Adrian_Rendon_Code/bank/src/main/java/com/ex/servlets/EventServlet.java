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

import com.bank.pojos.Employee;
import com.bank.pojos.Event;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/event")
public class EventServlet extends HttpServlet {

	static Service service = new Service();

	//  Get all user events to display on home screen. For admins, display all events
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("user"); // get logged user from session
		
		if (emp == null) {
			System.out.println("it is a null emp");
			emp = (Employee) session.getAttribute("emp");
		}
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		System.out.println("dep id " + emp.getEmail());
		
		// All employees that can approve events retrieve all events
		if (emp.getDepartmentId() > 1) {
			events = service.getAllEvents();
		} else {
			events = service.getEvents(emp);
		}

		ObjectMapper mapper = new ObjectMapper();
		String eventString = mapper.writeValueAsString(events);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(eventString);
	}

	// Creates a new event, do not create a new event if employee does not have a large enough balance
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		Service service = new Service();

		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(req.getInputStream(), Event.class);	

		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("user");
		event.setEmployeeId(emp.getId());

		// Cost of event cannot exceed available balance of employee
		if (emp.getBalance() - event.getCost() >= 0) {
			System.out.println("Creating new event");
			event = service.addEvent(event);
			// Update new balance
			int newBalance = emp.getBalance() - event.getCost();
			service.updateBalance(emp.getId(), newBalance);
			emp.setBalance(newBalance);
		} else {
			System.out.println("Cannot create that event");
		}

		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");

	}
}

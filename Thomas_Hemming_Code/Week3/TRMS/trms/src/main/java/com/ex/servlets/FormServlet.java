package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/submitEvent")
public class FormServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Service service = new Service();
		//Employee employee = new Employee();
		
		ObjectMapper mapper = new ObjectMapper();
		Event e = mapper.readValue(req.getInputStream(), Event.class);
		//Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		System.out.println(e.toString());
		Event event = service.addEvent(e);
		System.out.println(event);
		
		//HttpSession session = req.getSession(false);
		
		//Employee employee = (Employee) session.getAttribute("employee");
		
		String eventJSON = mapper.writeValueAsString(event);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.write(eventJSON);
		
		
	}

}
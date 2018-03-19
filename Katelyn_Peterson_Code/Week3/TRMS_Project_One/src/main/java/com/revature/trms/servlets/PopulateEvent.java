package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.service.EventService;

@WebServlet("/events")
public class PopulateEvent extends HttpServlet
{
	private static EventService eService = new EventService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		ArrayList<String> events = eService.getAllEvents();
		
		ObjectMapper mapper = new ObjectMapper();
		String formString = mapper.writeValueAsString(events);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(formString);
	}
}

package com.tailock.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tailock.dao.RequestDao;
import com.tailock.pojos.Employee;
import com.tailock.pojos.Request;
import com.tailock.service.Service;

@WebServlet("/createrequest")
public class CreateRequestServlet extends HttpServlet {
	
	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		RequestDao reqdao = new RequestDao();
		
		System.out.println("in create request servlet");
		
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		System.out.println(json);

		//2. instantiate jackson mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		//3. Convert received JSON string into appropriate object
		
		String[] requestInfo = mapper.readValue(json, String[].class); // read json string in and change to object
		int employee_id = Integer.parseInt(requestInfo[0]);
		String eventDate = requestInfo[1];
		int timeOfEvent = Integer.parseInt(requestInfo[2]);
		String description = requestInfo[3];
		int cost = Integer.parseInt(requestInfo[4]);
		int gradeFormat = Integer.parseInt(requestInfo[5]);
		int eventType = Integer.parseInt(requestInfo[6]);
		
		
		reqdao.createRequest(employee_id, eventDate, timeOfEvent, "Reston", description, cost, gradeFormat, 1, eventType);
	}

}
package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.service.Service;
import com.revature.trms.util.Validation;

@WebServlet("/validateSupervisor")
public class ValidateSupervisor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		String[] request = mapper.readValue(req.getInputStream(), String[].class);
		
		Validation validation = new Validation();
		boolean isValidFirstName = validation.validNameInput(request[0]);
		boolean isValidLastName = validation.validNameInput(request[1]);
		
		Service service = new Service();
		boolean exists = service.supervisorExists(request[0], request[1]);
		
		int supervisorId = 0;
		if(exists) {
			supervisorId = service.getSupervisorId(request[0], request[1]);
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(isValidFirstName + ":" + isValidLastName + ":" + exists + ":" + supervisorId));
	}
}

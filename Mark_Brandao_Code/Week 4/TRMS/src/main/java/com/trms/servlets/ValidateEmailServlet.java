package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.service.Service;
import com.trms.util.UserInputValidation;

@SuppressWarnings("serial")
@WebServlet("/validateemail")
public class ValidateEmailServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
		ObjectMapper mapper = new ObjectMapper();
		String email = mapper.readValue(req.getInputStream(), String.class);
		Service service = new Service();
		
		UserInputValidation uiv = new UserInputValidation();
		
		boolean exists = service.emailExists(email);
		boolean isValid = uiv.isValidInput(email, "email");
		
		int response = 0;
		
		if(isValid == false) {
			response = 1;
		} else if(exists == true) {
			response = 2;
		} else {
			response = 0;
		}
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(response));
	};
}

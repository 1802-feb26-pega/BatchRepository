package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.Form;
import com.revature.trms.service.FormService;

@WebServlet("/newRequest")
public class NewFormServlet extends HttpServlet
{
	private static FormService fService = new FormService();
	
	// Create new Form for Employee
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Form newForm = mapper.readValue(req.getInputStream(), Form.class);
		
		boolean success = false;
		
		//System.out.println(test);
		//System.out.println(newForm.toString());
		success = fService.addNewForm(newForm.getEmployId(), newForm);
		
		if(success)
		{
			System.out.println("Form successfully added to Database");
		}
		else
		{
			System.out.println("Either form was not submitted or servlet is being tested");
		}
		
		PrintWriter print = resp.getWriter();
		resp.setContentType("application/json");
	}
	
	// New Servlet to Update Form for Employee
}

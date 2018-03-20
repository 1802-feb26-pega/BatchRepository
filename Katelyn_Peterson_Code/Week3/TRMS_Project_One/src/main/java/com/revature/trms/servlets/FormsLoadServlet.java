package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

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

@WebServlet("/forms")
public class FormsLoadServlet extends HttpServlet
{
	private static FormService fService = new FormService();
	
	// Get Forms for Employee
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		ArrayList<Form> empForms = new ArrayList<>();
		//HashSet<Form> empForms = new HashSet<>();
		
		//System.out.println("IN Forms -- GET");
		HttpSession session = req.getSession();
		Employee user = (Employee) session.getAttribute("employee"); // get logged user from session
		
		//System.out.println(user.toString());
		
		// Add in 'else if's for Supervisor and Department Head
		if (user.getEmpTitle().matches("BenCo"))
		{
			empForms = (ArrayList<Form>) fService.getAllForms();
		}
		else
		{
			empForms = (ArrayList<Form>) fService.getEmpForms(user.getEmployeeId());
			
			// Check for subordinate forms
			ArrayList<Form> subForms = (ArrayList<Form>) fService.getSubordinateForms(user.getEmployeeId());
			
			if(!subForms.isEmpty())
			{
				for (int x = 0; x < subForms.size(); x++)
				{
					if(empForms.contains(subForms.get(x)))
					{
						continue;
					}
					else
					{
						empForms.add(subForms.get(x));
					}
				}
				
				// Check for Department forms
				ArrayList<Form> departForms = (ArrayList<Form>) fService.getDepartmentForms(user.getEmployeeId(), user.getDepartId());
				
				if(!departForms.isEmpty())
				{
					for (int x = 0; x < departForms.size(); x++)
					{
						if(empForms.contains(departForms.get(x)))
						{
							continue;
						}
						else
						{
							empForms.add(departForms.get(x));
						}
					}
				}
				
				//empForms.addAll(temp);
				//System.out.println("More Forms Added!");
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String formString = mapper.writeValueAsString(empForms);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(formString);
	}
	
	// New Servlet to Update Form for Employee
}

package com.proj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.pojos.Employee;
import com.proj.services.ClaimServices;

@WebServlet("/checkothers")
public class CheckOthersServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		work(req,resp);
	}

	public void work(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException,IOException{

		ClaimServices claimservice = new ClaimServices();

		HttpSession  session = req.getSession(false);
		Employee employee = (Employee) session.getAttribute("Employee");
						
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");		
		String empJSON = mapper.writeValueAsString(claimservice.direct_report(employee));
		out.write(empJSON);
		
	}
}

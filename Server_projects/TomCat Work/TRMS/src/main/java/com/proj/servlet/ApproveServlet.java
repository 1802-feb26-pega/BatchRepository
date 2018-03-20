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
import com.proj.pojos.Claim;
import com.proj.pojos.Employee;
import com.proj.services.ClaimServices;

@WebServlet("/approve")
public class ApproveServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		work(req,resp);
	}

	private void work(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		// TODO Auto-generated method stub
		ClaimServices claimservice = new ClaimServices();

		HttpSession  session = req.getSession(false);
		Employee employee = (Employee) session.getAttribute("Employee");

		ObjectMapper mapper = new ObjectMapper();
		int id = mapper.readValue(req.getInputStream(), Integer.class);
		String s;
		
		if(claimservice.update_claim(id,"APPROVED")){
			s = "1"; 
		}
		else{
			s = "0";
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");

		String empJSON = mapper.writeValueAsString(s);
		out.write(empJSON);	
	}
}

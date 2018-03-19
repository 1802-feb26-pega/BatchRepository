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
import com.proj.services.ClaimServices;

@WebServlet("/form")
public class FormServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("RECIVED A REQUEST!");
		work(req,resp);


	}

	private void work(HttpServletRequest req, HttpServletResponse resp)  throws IOException{
		ClaimServices claimservice = new ClaimServices();

		ObjectMapper mapper = new ObjectMapper();
		Claim claim = mapper.readValue(req.getInputStream(), Claim.class);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");

		String s;
		if(claimservice.addClaim(claim)) s = "0";
		else s = "1";
		
						
		String empJSON = mapper.writeValueAsString(s);
		HttpSession session = req.getSession();
		session.setAttribute("check",s);
		out.write(empJSON);	
		System.out.println("SENT JSON");
		
	}
}
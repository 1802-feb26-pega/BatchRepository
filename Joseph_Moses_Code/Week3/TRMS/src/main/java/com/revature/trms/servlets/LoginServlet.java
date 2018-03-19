package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	static Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		System.out.println("in the login servlet");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String[] empInfo = mapper.readValue(req.getInputStream(), String[].class);
		String email = empInfo[0];
		String password = empInfo[1];
		
		System.out.println(email +":" + password);
		
		Employee emp = service.login(email, password);
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		if(emp != null) {
			String empJSON = mapper.writeValueAsString(emp);
			
			HttpSession session = req.getSession();
			session.setAttribute("emp", emp);
			
			out.write(empJSON);
		}
		else {
			out.write("null"); //null as JSON string
		}

	}
}

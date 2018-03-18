package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	static Service service = new Service();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		System.out.println("register servlet 1");
		int value = -2;
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("register servlet 2");
		User u = mapper.readValue(req.getInputStream(),User.class);
		

		System.out.println("register servlet 3\n" + u.toString());
		//u = service.addUser(u);
		value = service.addUser(u);
		
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");

		out.write(mapper.writeValueAsString(u));
	}
}

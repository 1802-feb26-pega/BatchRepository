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
import com.trms.pojos.Request;
import com.trms.pojos.User;

@WebServlet("/newrequest")
public class NewRequestServlet extends HttpServlet
{
	static Service service = new Service();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		//System.out.println("new request servlet 1");
		int value = -2;
		
		ObjectMapper mapper = new ObjectMapper();
		//System.out.println("new request servlet 2");
		Request r = mapper.readValue(req.getInputStream(),Request.class);
		

		//System.out.println("new request servlet 3\n" + u.toString());
		value = service.addRequest(r,r.getU());
		System.out.println("asdfasdfasdf");
		
		PrintWriter out = res.getWriter();
		res.setContentType("application/json");

		out.write(mapper.writeValueAsString(r));
	}
}

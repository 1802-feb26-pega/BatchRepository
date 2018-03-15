package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.pojos.User;
import com.ex.services.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/users")
public class UserServlet extends HttpServlet{
	
	static DemoService service = new DemoService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<User> users = service.getAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(users.toArray());
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
	}
}

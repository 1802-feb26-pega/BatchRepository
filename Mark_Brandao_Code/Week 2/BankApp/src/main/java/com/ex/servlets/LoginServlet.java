package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bank.pojos.User;
import com.revature.bank.service.Login;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	static Login ll = new Login();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("In login servlet");
		
		// 1. Get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		// 2. Instantiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();
		
		// 3. Convert received JSON string into appropriate object
		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		String password = userInfo[1];
		User user = ll.login(username, password);
//		System.out.println(username + password);
//		System.out.println(user);
		PrintWriter out = res.getWriter();
		
		if(user != null) {
			String userJSON = mapper.writeValueAsString(user);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			out.write(userJSON);
			
		} else {
			out.write("null");
		}
		
		
		
	}
}

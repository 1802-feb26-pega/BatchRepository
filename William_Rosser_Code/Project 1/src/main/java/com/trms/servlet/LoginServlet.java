package com.trms.servlet;

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
import com.trms.pojos.BenCo;
import com.trms.pojos.DepartmentHead;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.User;
import com.trms.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();

		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();

		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		String password = userInfo[1];
		User user = Service.login(username, password);
	
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");

		if(user != null) {
			int type = 0;
			System.out.println(user.getClass());
			if (user instanceof DirectSupervisor) type = 1;
			else if (user instanceof DepartmentHead) type = 2;
			else if (user instanceof BenCo) type = 3;		
			String userJSON = mapper.writeValueAsString(user);
			userJSON = userJSON.substring(0,userJSON.length()-1) + ",\"type\":" + type +"}";
			System.out.println(userJSON);
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			System.out.println("Session value: " + session.getAttribute("user"));
			out.write(userJSON);
		} else {
			out.write("null");
		}
	}
}

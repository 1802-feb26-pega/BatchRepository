package com.delectamentum.trms.controller;

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

import org.mindrot.jbcrypt.BCrypt;

import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static EmployeeService es = new EmployeeService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String[] tks = mapper.readValue(req.getInputStream(), String[].class);
		String username = tks[0],
			   pw = tks[1];
		
		Employee e = es.getEmployeeByEmailAndPW(username, pw);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		if(e != null) {
			String outJson = mapper.writeValueAsString(e);
			System.out.println("out: " + e);
			
			HttpSession sess = req.getSession();
			sess.setAttribute("emp", e);
			
			out.write(outJson);
		}else {
			out.write("null");
		}
	}
}

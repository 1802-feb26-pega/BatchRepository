package com.pchase95.project1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.pojos.Employee;

@WebServlet("/ses_emp")
public class CurrentEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 7107272807178083531L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In emp sesssion servlet");
		Employee emp = (Employee) req.getSession().getAttribute("employee");		
		resp.getWriter().write(new ObjectMapper().writeValueAsString(emp));
	}
}

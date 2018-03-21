package com.pchase95.project1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.pojos.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2167399687594522160L;
	static EmployeeDAO empDAO = new EmployeeDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In login servlet");
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");

		Employee emp = empDAO.getByCredentials(email, pwd);
		
		ObjectMapper mapper = new ObjectMapper();
		if (emp != null) {
			HttpSession session = req.getSession();
			session.setAttribute("employee", emp);
			
			String empJSON = mapper.writeValueAsString(emp);
			resp.getWriter().write(empJSON);
		}
	}	
}

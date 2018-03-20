package com.pchase95.project1.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.SuperVisorResult;

@WebServlet("/supervisors")
public class SupervisorServlet extends HttpServlet {
	private static final long serialVersionUID = -8726292651625773051L;
	static DAO<Employee> empDAO = new EmployeeDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In supervisor servlet");
		List<Employee> emps = empDAO.getAll();
		List<SuperVisorResult> results = new LinkedList<>();
		for (Employee e : emps) {
			if (e.getSuperVisor() == null) {
				results.add(new SuperVisorResult(e.getId(), e.getEmail()));
			}
		}
		
		
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(results);
		resp.getWriter().write(json);
	}
}

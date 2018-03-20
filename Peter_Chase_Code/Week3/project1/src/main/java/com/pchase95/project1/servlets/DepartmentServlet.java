package com.pchase95.project1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.DepartmentDAO;
import com.pchase95.project1.pojos.Department;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = -1975591796246587057L;
	
	static DAO<Department> depDAO = new DepartmentDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in department servlet");
		List<Department> deps = depDAO.getAll();
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(deps);
		resp.getWriter().write(json);
	}
}

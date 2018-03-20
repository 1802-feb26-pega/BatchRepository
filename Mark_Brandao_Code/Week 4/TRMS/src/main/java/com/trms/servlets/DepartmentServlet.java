package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.dao.DepartmentDAO;
import com.trms.daoimpl.DepartmentDAOImpl;
import com.trms.pojos.Department;

@SuppressWarnings("serial")
@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DepartmentDAO dDao = new DepartmentDAOImpl();
		List<Department> depts = dDao.getAllDepartments();
		
		ObjectMapper mapper = new ObjectMapper();
		String deptString = mapper.writeValueAsString(depts);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(deptString);
		System.out.println(deptString);
	}
}

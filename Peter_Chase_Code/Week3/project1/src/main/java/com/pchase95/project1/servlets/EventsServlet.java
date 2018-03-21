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
import com.pchase95.project1.dao.EmployeeRBMTDao;
import com.pchase95.project1.dao.ReimbursmentDAO;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Reimbursment;

@WebServlet("/events")
public class EventsServlet extends HttpServlet {
	private static final long serialVersionUID = -7278889169614398206L;
	
	static DAO<Reimbursment> rbmtDAO = new ReimbursmentDAO();
	static EmployeeRBMTDao bridgeDAO = new EmployeeRBMTDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Reimbursment> results = rbmtDAO.getAll();
		Employee emp = (Employee) req.getSession().getAttribute("employee");
		List<Reimbursment> duplicates = bridgeDAO.getRBMTByEmployeeId(emp.getId());
		
		results.removeAll(duplicates);
		
		resp.setContentType("application/json");
		String json = new ObjectMapper().writeValueAsString(results);
		resp.getWriter().write(json);
	}
}

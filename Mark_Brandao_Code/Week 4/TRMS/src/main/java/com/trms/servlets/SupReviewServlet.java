package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.dao.ReimbursementFormDAO;
import com.trms.daoimpl.ReimbursementFormDAOImpl;
import com.trms.pojos.Employee;
import com.trms.pojos.JoinedQuery;

@WebServlet("/review")
@SuppressWarnings("serial")
public class SupReviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		
		int supervisorId = employee.getEmployeeId();
		ReimbursementFormDAO rfDao = new ReimbursementFormDAOImpl();
		List<JoinedQuery> requests = rfDao.getRequestsForApproval(supervisorId);
		
		ObjectMapper mapper = new ObjectMapper();
		String reimString = mapper.writeValueAsString(requests);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(reimString);
		System.out.println(reimString);
	}
}

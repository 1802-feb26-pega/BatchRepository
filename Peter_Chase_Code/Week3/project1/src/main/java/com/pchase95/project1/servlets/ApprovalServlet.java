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
import com.pchase95.project1.dao.ApprovalDAO;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.dao.EmployeeDAO;
import com.pchase95.project1.dao.ReimbursmentDAO;
import com.pchase95.project1.pojos.Approval;
import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Reimbursment;

@WebServlet("/approvals")
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 2115809563011079787L;

	static DAO<Reimbursment> rbmtDAO = new ReimbursmentDAO();
	static DAO<Employee> empDAO = new EmployeeDAO();
	static ApprovalDAO apvlDAO = new ApprovalDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("employee");
		List<Long> apvlIds = apvlDAO.getIdsByApplicant(emp.getId());
		List<Approval> approvals = new LinkedList<>();
		
		for (long l : apvlIds) {
			approvals.add(apvlDAO.getById(l));
		}
		
		resp.getWriter().write(new ObjectMapper().writeValueAsString(approvals));
	}
}

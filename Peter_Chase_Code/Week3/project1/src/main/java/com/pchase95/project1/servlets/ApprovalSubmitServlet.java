package com.pchase95.project1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pchase95.project1.dao.ApprovalDAO;
import com.pchase95.project1.dao.ApprovalTypeDAO;
import com.pchase95.project1.dao.DAO;
import com.pchase95.project1.pojos.Approval;
import com.pchase95.project1.pojos.ApprovalType;

@WebServlet("/submit_approval")
public class ApprovalSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = -8834034168426466830L;
	
	static DAO<Approval> apvlDAO = new ApprovalDAO();
	static DAO<ApprovalType> typeDAO = new ApprovalTypeDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		boolean approved = Boolean.parseBoolean(req.getParameter("approved"));
		
		Approval apvl = apvlDAO.getById(id);
		if (approved) {
			apvl.setType(typeDAO.getById(ApprovalType.ACCEPTED));
		} else {
			apvl.setType(typeDAO.getById(ApprovalType.DENIED));
		}
		
		apvlDAO.update(apvl.getId(), apvl);
	}
}

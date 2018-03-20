package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.dao.ReimbursementRequestDAOImpl;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;
import com.revature.trms.pojos.Status;
import com.revature.trms.service.Service;

@WebServlet("/approveRequest")
public class ApproveRequestServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Service service = new Service();
		
		ObjectMapper mapper = new ObjectMapper();
		int rrId = mapper.readValue(req.getInputStream(), Integer.class);

		ReimbursementRequestDAOImpl rrDao = new ReimbursementRequestDAOImpl();
		
		ReimbursementRequest updatedRequest = rrDao.getReimbursementRequestById(rrId);
		
		if(updatedRequest.getStatus().getStatusId() >= 4) {
			
		}
		else {
			updatedRequest.getStatus().setStatusId(updatedRequest.getStatus().getStatusId() + 1);
		}
		
		rrDao.updateReimbursementRequest(updatedRequest);
		

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(updatedRequest));
		
	}
}

package com.revature.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;
import com.revature.trms.service.Service;

@WebServlet("/requestSubmission")
public class RequestSubmissionServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String[] requestInfo = mapper.readValue(req.getInputStream(), String[].class);
		
		String location = requestInfo[0];
		String description = requestInfo[1];
		int typeOfEventId = Integer.parseInt(requestInfo[2]);
		double cost = Double.parseDouble(requestInfo[3]);
		int workTimeMissed = Integer.parseInt(requestInfo[4]);
		double expectedReimbursment = Double.parseDouble(requestInfo[5]);
		Date requestDate = Date.valueOf(requestInfo[6]);
		Date startDate = Date.valueOf(requestInfo[7]);
		int gradingFormatId = Integer.parseInt(requestInfo[8]);
		int passingGrade = Integer.parseInt(requestInfo[9]);
		String justification = requestInfo[10];
		
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("emp");
		
		emp.setAmountAvailable(emp.getAmountAvailable() - expectedReimbursment);
		emp.setPending(expectedReimbursment);
		
		
		int priorityId;
		if(ChronoUnit.DAYS.between(LocalDate.parse(requestInfo[7]), LocalDate.parse(requestInfo[6])) < 7) {
			priorityId = 1;
		}
		else {
			priorityId = 2;
		}
		
		int statusId = 1;
		
		ReimbursementRequest rr = new ReimbursementRequest(emp.getEmpId(), requestDate, startDate, location, description,
				typeOfEventId, cost, gradingFormatId, passingGrade, justification, workTimeMissed, expectedReimbursment, priorityId, statusId);
		
		Service service = new Service();
		
		
		rr = service.addReimbursmentRequest(rr);
		emp = service.updateEmployee(emp);
		
		session.setAttribute("emp", emp);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(emp));
	}
}

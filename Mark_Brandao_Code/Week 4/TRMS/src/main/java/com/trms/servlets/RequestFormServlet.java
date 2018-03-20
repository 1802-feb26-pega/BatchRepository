package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.dao.EmployeeDAO;
import com.trms.dao.EventDAO;
import com.trms.dao.ReimbursementFormDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.daoimpl.EventDAOImpl;
import com.trms.daoimpl.ReimbursementFormDAOImpl;
import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.ReimbursementForm;
import com.trms.service.Service;

@SuppressWarnings("serial")
@WebServlet("/requestform")
public class RequestFormServlet extends HttpServlet {
	
	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String[] form = mapper.readValue(req.getInputStream(), String[].class);
		
		String location = form[0];
		String description = form[1];
		int eventType = Integer.parseInt(form[2]);
		double cost = Double.parseDouble(form[3]);
		int timeMissed = Integer.parseInt(form[4]);
		double expectedReimbursement = Double.parseDouble(form[5]);
//		Date startDate = Date.valueOf(form[6]);
		int gradeFormat = Integer.parseInt(form[7]);
		String justification = form[8];
		
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		employee.setAvailableReimbursement(employee.getAvailableReimbursement() - expectedReimbursement);
		employee.setPendingReimbursement(expectedReimbursement + employee.getPendingReimbursement());
		
		boolean urgent;
		if(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(form[6])) < 14) {
			urgent = true;
		} else urgent = false;
		
		int statusId = 1;
		
		Event event = new Event();
		event.setEventCoverageId(eventType);
		event.setDescription(description);
		event.setEventLocation(location);
		
		
		ReimbursementForm rf = new ReimbursementForm();
		rf.setEmployeeId(employee.getEmployeeId());
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		rf.setDateSubmitted(date);
		event.setEventDate(date);
		rf.setTimeSubmitted(new Timestamp(System.currentTimeMillis()));
		rf.setJustification(justification);
		rf.setGradeFormatId(gradeFormat);
		rf.setStatusId(statusId);
		rf.setTimeMissed(timeMissed);
		rf.setTotalCost(cost);
		rf.setProjectedReimbursement(expectedReimbursement);
		rf.setUrgent(urgent);
		rf.setExceedsValue(false);
		
		EventDAO eventDao = new EventDAOImpl();
		event = eventDao.addEvent(event);
		
		rf.setEventId(event.getEventId());
		
		ReimbursementFormDAO rfDao = new ReimbursementFormDAOImpl();
		rfDao.addReim(rf);
		
		EmployeeDAO employeeDao = new EmployeeDAOImpl();
		employee = employeeDao.updateEmployee(employee);
		
		session.setAttribute("employee", employee);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(mapper.writeValueAsString(employee));
	}
}

package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.dao.EmployeeDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.pojos.Employee;
import com.trms.service.Service;
import com.trms.util.UserInputValidation;

@SuppressWarnings("serial")
@WebServlet("/supervisor")
public class ValidateSupervisorServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String[] supervisor= mapper.readValue(req.getInputStream(), String[].class);
		String firstname = supervisor[0];
		String lastname = supervisor[1];
		
		Service service = new Service();
		
		UserInputValidation uiv = new UserInputValidation();
		Employee SS = new Employee();
		boolean exists = service.nameExists(firstname, lastname);
		boolean isSup = false;
		if(exists) {
			EmployeeDAO eDao = new EmployeeDAOImpl();
			SS = eDao.getEmployeeByName(firstname, lastname);
			System.out.println(SS.getLevelId());
			switch(SS.getLevelId()) {
			case 2: isSup = true; break;
			case 5: isSup = true; break;
			}
		}
		
		boolean firstIsName = uiv.isValidInput(firstname, "name");
		boolean lastIsName = uiv.isValidInput(lastname, "name");
		int response = 0;
		
		if(isSup) {
			response = SS.getEmployeeId();
		} else if((firstIsName == false) || (lastIsName == false)) {
			response = -1;
		} else if(exists == false) {
			response = -2;
		} else {
			response = -2;
		}
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		out.write(mapper.writeValueAsString(response));
	}
}

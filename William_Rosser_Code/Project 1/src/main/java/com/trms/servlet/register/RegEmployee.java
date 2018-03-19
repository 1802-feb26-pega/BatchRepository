package com.trms.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.Employee;
import com.trms.service.Service;

@WebServlet("/reg_employee")
public class RegEmployee extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Employee e = mapper.readValue(req.getInputStream(), Employee.class);
		e = (Employee) Service.addUser(e, 0);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		if (e!=null) {
			String uJSON = Service.userToJSON(e);
			out.write(uJSON);
		} else {
			out.write("null");
		}
	}
}

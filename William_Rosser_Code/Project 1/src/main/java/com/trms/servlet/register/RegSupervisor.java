package com.trms.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.DirectSupervisor;
import com.trms.pojos.Employee;
import com.trms.service.Service;

@WebServlet("/reg_supervisor")
public class RegSupervisor extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service service = new Service();
		ObjectMapper mapper = new ObjectMapper();
		DirectSupervisor e = mapper.readValue(req.getInputStream(), DirectSupervisor.class);
		System.out.println(e.toString());
		e = (DirectSupervisor) Service.addUser(e, 1);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		if (e!=null) {
			String uJSON = mapper.writeValueAsString(e);
			out.write(uJSON);
		} else {
			out.write("null");
		}
	}
}

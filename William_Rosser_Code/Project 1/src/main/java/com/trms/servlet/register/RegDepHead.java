package com.trms.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.DepartmentHead;
import com.trms.service.Service;

@WebServlet("/reg_dephead")
public class RegDepHead extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		DepartmentHead e = mapper.readValue(req.getInputStream(), DepartmentHead.class);
		System.out.println(e.toString());
		e = (DepartmentHead) Service.addUser(e, 2);
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

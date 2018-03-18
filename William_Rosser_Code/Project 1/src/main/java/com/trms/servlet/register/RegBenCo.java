package com.trms.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.BenCo;
import com.trms.pojos.DepartmentHead;
import com.trms.service.Service;

@WebServlet("/reg_benco")
public class RegBenCo extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Service service = new Service();
		ObjectMapper mapper = new ObjectMapper();
		BenCo e = mapper.readValue(req.getInputStream(), BenCo.class);
		System.out.println(e.toString());
		e = (BenCo) Service.addUser(e, 3);
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

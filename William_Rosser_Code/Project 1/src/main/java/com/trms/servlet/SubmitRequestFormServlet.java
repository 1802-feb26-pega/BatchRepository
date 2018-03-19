package com.trms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.TRForm;
import com.trms.pojos.User;
import com.trms.service.Service;

@WebServlet("/submit_request")
public class SubmitRequestFormServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		TRForm trf = mapper.readValue(req.getInputStream(), TRForm.class);
		System.out.println(trf);
		User u = (User) req.getSession().getAttribute("user");
		boolean good = Service.addRequest(trf, u);
		resp.setContentType("application/json");
		if (good) {
			String json = mapper.writeValueAsString(trf);
			resp.getWriter().write(json);
		} else {
			resp.getWriter().write("null");
		}
	}
}

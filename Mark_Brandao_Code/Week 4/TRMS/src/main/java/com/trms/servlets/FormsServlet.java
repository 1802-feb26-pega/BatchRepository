package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trms.pojos.Employee;
import com.trms.pojos.ReimbursementForm;
import com.trms.service.Service;

@SuppressWarnings("serial")
@WebServlet("/requests")
public class FormsServlet extends HttpServlet {
	
	static Service service = new Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Employee employee = (Employee) session.getAttribute("employee");
		
		System.out.println(employee.toString());
		
		List<ReimbursementForm> reims = service.getForms(employee);
		
		ObjectMapper mapper = new ObjectMapper();
		String reimString = mapper.writeValueAsString(reims);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(reimString);
		System.out.println(reimString);
	}
}

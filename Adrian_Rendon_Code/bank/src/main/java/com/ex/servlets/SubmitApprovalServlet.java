package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.pojos.Employee;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/submitApproval")
public class SubmitApprovalServlet extends HttpServlet {

	static Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();

		ObjectMapper mapper = new ObjectMapper();

		String[] appInfo = mapper.readValue(json, String[].class);
		String sid = appInfo[0];
		String approval = appInfo[1];
		int id = Integer.parseInt(sid);

		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("user");

		PrintWriter out = resp.getWriter();
		if (emp.getDepartmentId() > 1) {

			if (approval.equals("yes")) {
				int send = 1;
				service.updateApproval(id, send);
			} else if (approval.equals("no")) {
				int send = 0;
				service.updateApproval(id, send);
			} else {
				System.out.println("error not valid input");
				out.write("null");
			}
		} else {
			out.write("null");
		}

	}
}
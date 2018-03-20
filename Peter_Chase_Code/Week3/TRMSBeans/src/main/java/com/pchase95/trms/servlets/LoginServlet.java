package com.pchase95.trms.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2167399687594522160L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = req.getReader();
		
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		pw.println("waddup buddio");
	}
}

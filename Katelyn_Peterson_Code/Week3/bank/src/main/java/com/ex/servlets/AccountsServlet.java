package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.pojos.Account;
import com.bank.pojos.User;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet
{
	static Service service = new Service();
	// Get accounts per user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		System.out.println("In Accounts -- Get");
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		System.out.println(user.toString());
		
		ArrayList<Account> accounts = service.getAccounts(user);
		
		ObjectMapper mapper = new ObjectMapper();
		String accString = mapper.writeValueAsString(accounts);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(accString);
	}
	
	// Create user account
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	// Update account
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
}

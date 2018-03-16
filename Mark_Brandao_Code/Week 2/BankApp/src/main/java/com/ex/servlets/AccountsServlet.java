package com.ex.servlets;

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
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;
import com.revature.bank.service.Login;


@SuppressWarnings("serial")
@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {

	Login ll = new Login();
	
	// get accounts per user
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		System.out.println(user.toString());
		
		List<Account> accounts = ll.getAccountsByUser(user);
		
		ObjectMapper mapper = new ObjectMapper();
		String accString = mapper.writeValueAsString(accounts);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(accString);
		System.out.println(accounts);
	}

	// create new account
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	// update account
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}


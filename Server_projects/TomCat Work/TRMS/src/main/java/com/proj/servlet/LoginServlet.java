package com.proj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.pojos.Employee;
import com.proj.services.EmployeeServices;

/*A servlet used for the login page
 * accepts user name and password
 * then attempts to log into TRMS
 * 
 * 
 * 
 * 
 */
public class LoginServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException,ServletException{
		
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		
		EmployeeServices employService = new EmployeeServices();
		
		Employee employee = employService.validateEmployee(name, password);
		
		
		//Need to use AJAX here!
		//If employee credentials is correct log in
		if(employee != null){
			HttpSession session = req.getSession();
			session.setAttribute("Employee", employee);
			
			
			req.getRequestDispatcher("loggedin.html").forward(req, resp);
		}
		else{
			//code here if incorrect
			//returns user to the Login screen
		}
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException,ServletException{
		
	}
}

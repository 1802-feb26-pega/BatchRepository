package com.proj.servlet;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.pojos.Employee;
import com.proj.services.EmployeeServices;

/*A servlet used for the login page
 * accepts user name and password
 * then attempts to log into TRMS
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	EmployeeServices employServices= new EmployeeServices();
	Employee employee;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException,ServletException{		

		work(req,resp);

	}		

	public void work(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();

		//System.out.println(json);

		//2. instantiate jackson mapper 
		ObjectMapper mapper = new ObjectMapper();

		//3. Convert received JSON string into appropriate object

		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		String password = userInfo[1];		
		
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		
		employee = employServices.validateEmployee(username, password);

		if(employee != null){
			String empJSON = mapper.writeValueAsString(employee);
			HttpSession session = req.getSession(true);
			session.setAttribute("Employee",employee);
			out.write(empJSON);
		}else{
			out.write("null");
		}
		
	}
}

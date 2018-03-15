package com.proj.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		work(req);

	}		

	public void work(HttpServletRequest req) throws IOException{
		//1. get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();

		System.out.println(json);

		//2. instantiate jackson mapper 
		ObjectMapper mapper = new ObjectMapper();

		//3. Convert received JSON string into appropriate object

		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		String password = userInfo[1];		
		
		Login(username,password);
		
	}
	public void Login(String username, String password){
		if(employee != null){
			System.out.println("IT WORKS");
		}else{
			
		}
			
	}
}

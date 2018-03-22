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

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.pojos.Employee;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	static Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in login servlet");


		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = br.readLine();

		System.out.println(json);


		ObjectMapper mapper = new ObjectMapper();

		System.out.println('1');

		String[] userInfo = mapper.readValue(json, String[].class);
		System.out.println('2');
		String email = userInfo[0];
		System.out.println('3');
		String password = userInfo[1];
		System.out.println('4');

		Employee employee = service.login(email, password);
		System.out.println('5');
		PrintWriter out = response.getWriter();
		System.out.println('6');
		response.setContentType("application/json");
		System.out.println('7');
		if(employee!= null) {
			System.out.println("if 8");
			String userJSON = mapper.writeValueAsString(employee);

			HttpSession session = request.getSession();
			session.setAttribute("user", employee);

			out.write(userJSON);

		}
		else {
			System.out.println("else 9");
			out.write("null"); 
		}
		
	}

}

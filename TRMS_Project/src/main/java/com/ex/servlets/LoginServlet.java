package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.SynchronousQueue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.pojos.User;
import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	static Service service = new Service();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		//System.out.println("in login servlet");

		//1) get request body from request object
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		System.out.println(json);

		//2) instantiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();

		//3) convert received JSON string into appropriate object
		String[] userInfo = mapper.readValue(json,String[].class );
		String username = userInfo[0];
		String password = userInfo[1];

		
		User user = service.login(username, password);
		

		PrintWriter out = res.getWriter();
		res.setContentType("application/json");

		if(user!=null)
		{
			String userJson = mapper.writeValueAsString(user);
			HttpSession session = req.getSession();
			session.setAttribute("user",user);

			//System.out.println("JSON: " + userJson);
			out.write(userJson);
		}else
		{
			//System.out.println("printing null inside loginservlet"); //null as json string
			out.write("null");
		}



	}//doPost


}//service



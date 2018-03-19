package com.proj.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.services.EventService;

@WebServlet("/getEvents")
public class GetEvents extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		work(req,resp);


}

	private void work(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		EventService eService = new EventService();
		ObjectMapper mapper = new ObjectMapper();
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
						
		String empJSON = mapper.writeValueAsString(eService.getEvents());
		out.write(empJSON);	
	
	}

}

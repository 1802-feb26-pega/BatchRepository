package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/submitGrade")
public class SubmitGradeServlet extends HttpServlet {

	static Service service = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String[] gradeInfo = mapper.readValue(json, String[].class);
		String sid = gradeInfo[0];
		String grade = gradeInfo[1];
		
		int id = Integer.parseInt(sid);
		
		service.updateGrade(id, grade);
	}
}

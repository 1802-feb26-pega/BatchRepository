package com.delectamentum.trms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.delectamentum.trms.document.Claim;
import com.delectamentum.trms.document.Employee;
import com.delectamentum.trms.service.ClaimService;
import com.delectamentum.trms.service.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebServlet("/claims/*")
public class ClaimController extends HttpServlet {
	
	private static ClaimService csv = new ClaimService();
	
	private static final String[] TYPES = {
			"Unspecified",
			"University Course",
			"Seminar",
			"Certification Preparation Class",
			"Certification Exam",
			"Technical Training",
			"Other (Specified in description)",
			},
			STATUSES = {
					"Denied",
					"Awaiting Supervisor Approval",
					"Approved by Supervisor, Awaiting Dept. Head Approval",
					"Approved by Dept. Head, Awaiting Benefits Coordinator Approval",
					"Approved by Benefits Coordinator"
			},
			GRADETYPES = {
					"Minimum Passing Grade",
					"Pass/Fail",
					"Presentation"
			};
	
	private static final double[] TYPE_FACTORS = {
			1,
			.8,
			.6,
			.75,
			1,
			.9,
			.3
	};
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("at get");
		System.out.println(req.getRequestURI());
		if(req.getSession().getAttribute("emp") != null) {
			switch(req.getRequestURI()) {
			case "/trms/claims/personal":
				getOwnClaims(req,resp);
				break;
			case "/trms/claims/approvable":
				getApprovableClaims(req, resp);
				break;
			default:
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				break;
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("at post");
		System.out.println(req.getRequestURI());
		if(req.getSession().getAttribute("emp") != null) {
			switch(req.getRequestURI()) {
			case "/trms/claims/submit":
				createClaim(req,resp);
				break;
			case "/trms/claims/update":
				updateClaim(req,resp);
				break;
			default:
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				break;
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	private static void updateClaim(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Employee e = (Employee) req.getSession().getAttribute("emp");
		String[] tks = mapper.readValue(req.getInputStream(), String[].class);
		int claimNumber = Integer.parseInt(tks[0]);
		int decision = tks[1].equals("yes") ? e.getType() : 0;
		Claim target = csv.getClaimById(claimNumber);
		if(target != null) {
			target.setStatus(decision);
			if(decision > 0)
				csv.approveRequest(claimNumber, e);
			else
				csv.denyRequest(claimNumber, e);
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	private static void createClaim(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		Claim c = mapper.readValue(req.getInputStream(), Claim.class);
		
		c = csv.submitNewRequest(c);
		System.out.println(c);
	}
	
	private static void getOwnClaims(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		ObjectMapper om = new ObjectMapper();
		Gson g = new Gson();
		Employee e = (Employee) req.getSession().getAttribute("emp");
		resp.setContentType("application/json");
		
		System.out.println("Personal reimbursement claims requested by: \n\t" + e);
		
		List<Claim> ownclaims = csv.getAllOwnClaims(e);
		if(ownclaims != null) {
			processTypes(ownclaims);
			String outJson = g.toJson(ownclaims);//om.writeValueAsString("{ \"data\":" + ownclaims_ar + "}");
			System.out.println("{\"data\":" + outJson + "}");
			out.write("{\"data\":" + outJson + "}");
		} else {
			out.write("{\"data\":[]}");
		}
	}
	
	private void getApprovableClaims(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		ObjectMapper om = new ObjectMapper();
		Gson g = new Gson();
		Employee e = (Employee) req.getSession().getAttribute("emp");
		resp.setContentType("application/json");
		
		System.out.println("Personal reimbursement claims requested by: \n\t" + e);
		
		List<Claim> approvables = csv.getApprovableRequests(e);
		if(approvables != null) {
			processTypes(approvables);
			String outJson = g.toJson(approvables);//om.writeValueAsString("{ \"data\":" + ownclaims_ar + "}");
			System.out.println("{\"data\":" + outJson + "}");
			out.write("{\"data\":" + outJson + "}");
		} else {
			out.write("{\"data\":[]}");
		}
			
	}
	
	private static void processTypes(List<Claim> claims) {
		for(Claim c : claims) {
			c.setAmount(c.getAmount() * TYPE_FACTORS[c.getEventType()]);
			c.setEventTypeStr(TYPES[c.getEventType()]);
			c.setGradeTypeStr(GRADETYPES[c.getGradeType()]);
			c.setStatusStr(STATUSES[c.getStatus()]);
		}
	}
}


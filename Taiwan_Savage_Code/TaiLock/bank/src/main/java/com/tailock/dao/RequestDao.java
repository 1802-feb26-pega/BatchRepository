package com.tailock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tailock.pojos.Employee;
import com.tailock.pojos.Request;
import com.tailock.util.ConnectionFactory;

public class RequestDao {
	
	/**
	 * need to be able to do CRUD operationss on requests and update the request info table
	 */
	
	public int getMaxReqId() {
		int maxId = 0;
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select request_id from reimbursement_request";
			Statement stmt = conn.createStatement();
			ResultSet info = stmt.executeQuery(sql);
			
			while(info.next()){
				if (info.getInt(1) > maxId) {
					maxId = info.getInt(1);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maxId;
	}
	
	
	public void createRequest(int employee_id, String eventStartDate, int timeOfEvent, String location, String description,
			int costOfEvent, int gradeFormat, int requestType, int eventType) {
		try (Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			
			// Create an instance of SimpleDateFormat used for formatting 
			// the string representation of date (month/day/year)
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			// Get the date today using Calendar object.
			Date today = Calendar.getInstance().getTime();        
			// Using DateFormat format method we can create a string 
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			int reimAmount = 0;
			switch(eventType) {
			case(1):
				reimAmount = (int)(costOfEvent * .8);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			case(2):
				reimAmount = (int)(costOfEvent * .6);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			case(3):
				reimAmount = (int)(costOfEvent * .75);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			case(4):
				reimAmount = (int)(costOfEvent);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			case(5):
				reimAmount = (int)(costOfEvent * .9);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			case(6):
				reimAmount = (int)(costOfEvent * .3);
				if (reimAmount > 1000) {
					reimAmount = 1000;
				}
				break;
			}
			
			
			
			String sql = "insert into reimbursement_request (request_id, employee_id, event_start_date, time_of_event, location_of_event, request_description, cost, grade_format,"
					+ " request_type, event_type, date_of_request, status, priority, time_missed, reim_amount)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getMaxReqId() + 1);
			ps.setInt(2, employee_id);
			ps.setString(3, eventStartDate);
			ps.setInt(4, timeOfEvent);
			ps.setString(5, location);
			ps.setString(6, description);
			ps.setInt(7, costOfEvent);
			ps.setInt(8, gradeFormat);
			ps.setInt(9, requestType);
			ps.setInt(10, eventType);
			ps.setString(11, reportDate);
			ps.setInt(12, 1);
			ps.setInt(13, 1);
			ps.setInt(14, 1);
			ps.setInt(15, reimAmount);
			int updated = ps.executeUpdate();
			if(updated > 0) {
				System.out.println("Added " + updated + " new employee");
			}
			else {
				System.out.println("Wasn't added for some reason");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Request> getAllRequests() {
		Request req = new Request();
		int maxId = 0;
		List<Request> requests = new ArrayList();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursement_request";
			Statement stmt = conn.createStatement();
			ResultSet info = stmt.executeQuery(sql);
			

			while(info.next()){
				req = new Request(info.getInt(2), info.getString(3), info.getInt(4), info.getString(5), info.getString(6),
			info.getInt(7), info.getInt(11), info.getInt(12), info.getInt(13));
				req.setRequest_id(info.getInt(1));
				req.setReimAmount(info.getShort(8));
				req.setTimeOfEvent(info.getInt(9));
				req.setDaysMissed(info.getInt(10));
				req.setPriority(info.getInt(14));
				req.setStatus(15);
				requests.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public List<Request> getAllUserRequests(Employee emp) {
		Request req = new Request();
		int maxId = 0;
		List<Request> requests = new ArrayList();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursement_request where employee_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			ResultSet info = stmt.executeQuery();
			
			
			while(info.next()){
				System.out.println(info.getInt(1));
				req = new Request(info.getInt(2), info.getString(3), info.getInt(5), info.getString(6), info.getString(7), info.getInt(8), info.getInt(12), info.getInt(13), info.getInt(14));
				
				req.setRequest_id(info.getInt(1));
				req.setDateOfRequest(info.getString(4));
				req.setReimAmount(info.getShort(9));
				req.setTimeOfEvent(info.getInt(10));
				req.setDaysMissed(info.getInt(11));
				req.setPriority(info.getInt(15));
				req.setStatus(info.getInt(16));
				requests.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public Request getRequest(int request_id) {
		Request req = new Request();
		int maxId = 0;
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursement_request where request_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, request_id);
			ResultSet info = stmt.executeQuery(sql);
			

			while(info.next()){
				req = new Request(info.getInt(2), info.getString(3), info.getInt(4), info.getString(5), info.getString(6),
			info.getInt(7), info.getInt(11), info.getInt(12), info.getInt(13));
				req.setRequest_id(info.getInt(1));
				req.setReimAmount(info.getShort(8));
				req.setTimeOfEvent(info.getInt(9));
				req.setDaysMissed(info.getInt(10));
				req.setPriority(info.getInt(14));
				req.setStatus(15);
				return req;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return req;
	}
	
/*	public Request updateRequest() {
		
	}*/
	
	//can't delete requests rn
	public void deleteRequest() {
		
	}

}

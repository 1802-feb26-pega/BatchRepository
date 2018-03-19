package com.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee getEmployeeById(int employeeId) {
		Employee e = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from employee where employee_id =  ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet info = ps.executeQuery();

			while(info.next()){
				e.setEmployeeId(info.getInt(1));
				e.setFirstname(info.getString(2));
				e.setLastname(info.getString(3));
				e.setPosition(info.getInt(4));
				e.setUsername(info.getString(5));
				e.setPassword(info.getString(6));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if(e.getEmployeeId() == 0) return null;
		return e;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee e = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			username = username.toLowerCase();
			String sql = "select * from employee where lower(username) = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				e.setEmployeeId(info.getInt(1));
				e.setFirstname(info.getString(2));
				e.setLastname(info.getString(3));
				e.setPosition(info.getInt(4));
				e.setUsername(info.getString(5));
				e.setPassword(info.getString(6));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if(e.getEmployeeId() == 0) return null;
		return e;
	}

	@Override
	public ArrayList<Event> getEventsByEmployee(Employee employee) {
		ArrayList<Event> events = new ArrayList<Event>();
		
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from event where employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeId());
			ResultSet info = ps.executeQuery();

			while(info.next()){
				Event temp = new Event();
				temp.setEventId(info.getInt(1));
				System.out.println("Account " + temp.getEventId());
				temp.setDateCreated(info.getDate(2));
				temp.setDateScheduled(info.getDate(3));
				temp.setEventLocation(info.getString(4));
				temp.setEventCost(info.getInt(5));
				temp.setEventTypeId(info.getInt(6));
				temp.setEmployeeId(info.getInt(7));
				temp.setGrade(info.getInt(8));
				events.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return events;
	}
	 
}

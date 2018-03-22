package com.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;
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
				temp.setDateCreated(info.getTimestamp(2));
				temp.setDateScheduled(info.getTimestamp(3));
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
	
	@Override
	public ArrayList<Reimbursement> getReimbursementsByPending() {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursement where re_status = 'pending'";
			Statement s = conn.createStatement();
			
			ResultSet info = s.executeQuery(sql);

			while(info.next()){
				Reimbursement temp = new Reimbursement();
				temp.setReId(info.getInt(1));
				temp.setEmployeeId(info.getInt(2));
				temp.setEventId(info.getInt(3));
				temp.setJustification(info.getString(4));
				temp.setSuperApp(info.getInt(5));
				temp.setDepHeadApp(info.getInt(6));
				temp.setBenCoApp(info.getInt(7));
				temp.setRequestedAmount(info.getInt(8));
				temp.setAlternateAmount(info.getInt(9));
				temp.setReStatus(info.getString(10));
				reimbursements.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return reimbursements;
	}

	@Override
	public ArrayList<Reimbursement> getReimbursementsByEmployee(Employee employee) {
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from reimbursement where employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeId());
			ResultSet info = ps.executeQuery();

			while(info.next()){
				Reimbursement temp = new Reimbursement();
				temp.setReId(info.getInt(1));
				temp.setEmployeeId(info.getInt(2));
				temp.setEventId(info.getInt(3));
				temp.setJustification(info.getString(4));
				temp.setSuperApp(info.getInt(5));
				temp.setDepHeadApp(info.getInt(6));
				temp.setBenCoApp(info.getInt(7));
				temp.setRequestedAmount(info.getInt(8));
				temp.setAlternateAmount(info.getInt(9));
				temp.setReStatus(info.getString(10));
				reimbursements.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return reimbursements;
	}
	 
	@Override
	public int showPosition(Employee employee) {
		int position = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{CALL show_position(?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, employee.getEmployeeId());
			int value = cstmt.executeUpdate();

			position = cstmt.getInt(2);
			if(value < 1) {
				System.out.println("Couldn't get position for that employee!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return position;
	}
}

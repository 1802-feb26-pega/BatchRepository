package com.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trms.pojos.Employee;
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
	 
}

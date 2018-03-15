package com.proj.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.proj.pojos.Employee;
import com.proj.util.ConnectionFactory;

public class DatabaseDao implements DatabaseDaoImpl{
	ArrayList<Employee> emplist = new ArrayList<Employee>();
	Employee employee;

	public Employee getEmployeeLogin(String s) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT * FROM EMPLOYEES WHERE USERNAME = " + s;

			PreparedStatement statement = conn.prepareStatement(sql);


			ResultSet crs = statement.executeQuery();

			while(crs.next()) {
				employee.setKey(crs.getInt(1));
				employee.setFirstname(crs.getString(2));
				employee.setLastname(crs.getString(3));
				employee.setUsername(crs.getString(4));
				employee.setPassword(crs.getString(5));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		
		return employee;
	}
	public ArrayList<Employee> getEmployeesUsername() {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT USERNAME FROM CUSTOMER";

			Statement statement = conn.createStatement();


			ResultSet crs = statement.executeQuery(sql);

			while(crs.next()) {
				Employee old = new Employee();
				old.setUsername(crs.getString(1));
				emplist.add(old);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return emplist;
	}

}


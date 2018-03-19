package com.proj.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.proj.pojos.Employee;
import com.proj.util.ConnectionFactory;

public class EmployeeDao implements DatabaseDaoInterfaces{
	ArrayList<Employee> emplist = new ArrayList<Employee>();
	Employee employee = new Employee();

	//Grabs the an employee from the database if user
	//and password is correct
	public Employee getEmployeeLogin(String usrname, String password) {
		// TODO Auto-generated method stub

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setString(1, usrname);
			p_statement.setString(2, password);			

			ResultSet crs = p_statement.executeQuery();

			while(crs.next()) {
				employee.setKey(crs.getInt(1));
				employee.setFirstname(crs.getString(2));
				employee.setLastname(crs.getString(3));
				employee.setUsername(crs.getString(4));
				employee.setPassword(crs.getString(5));
				employee.setReportsto(crs.getInt(6));
				employee.setTotal(crs.getDouble(7));
				employee.setAwarded(crs.getDouble(8));
				employee.setDept(crs.getInt(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		if(employee.getKey() ==0) return null;
		return employee;
	}

	//Double check this later
	public ArrayList<Employee> getEmployeesUsername() {
		// TODO Auto-generated method stub

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT USERNAME, PASSWORD FROM EMPLOYEE;";

			Statement statement = conn.createStatement();


			ResultSet crs = statement.executeQuery(sql);

			while(crs.next()) {
				Employee old = new Employee();
				old.setUsername(crs.getString(1));
				old.setPassword(crs.getString(2));
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

	public String getSuper(Employee emp) {
		// TODO Auto-generated method stub
		String result = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT SUPER FROM EMPLOYEE WHERE EMP_ID = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, emp.getKey());

			ResultSet crs = statement.executeQuery();

			while(crs.next()) {
				result = crs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		System.out.println("Sending super info");
		return result;
	}
}





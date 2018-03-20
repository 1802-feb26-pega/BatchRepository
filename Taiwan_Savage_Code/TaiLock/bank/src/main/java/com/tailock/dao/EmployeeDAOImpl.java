package com.tailock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tailock.pojos.Employee;
import com.tailock.pojos.Request;
import com.tailock.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	RequestDao reqdao = new RequestDao();
	
	//gets a specific employee
	@Override
	public Employee getEmployee(String email) {
		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			email = email.toLowerCase();
			String sql = "select * from employee where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				emp.setEmployeeId(info.getInt(1));
				emp.setUsername(info.getString(2));
				emp.setFirstName(info.getString(3));
				emp.setLastName(info.getString(4));
				emp.setEmail(info.getString(5));
				emp.setPassword(info.getString(10));
				emp.setRequests(reqdao.getAllUserRequests(emp));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//updates the stored version of an employee
	public Employee updateEmployee(Employee emp) {
		return emp;
	}
	
	//get the maximum employee id so we can increment when a new employee is added
	public int getMaxEmpId() {
		Employee emp = new Employee();
		int maxId = 0;
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select employee_id from employee";
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
	
	public List<Employee> getEmployeesDirectlyUnder(int employee_id) {
		Employee emp = new Employee();
		try(Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "select * from employee where supervisor_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee_id);
			ResultSet info = ps.executeQuery();
			
			List<Employee> emps = new ArrayList();
			
			while(info.next()){
				emp.setEmployeeId(info.getInt(1));
				emp.setUsername(info.getString(2));
				emp.setFirstName(info.getString(3));
				emp.setLastName(info.getString(4));
				emp.setEmail(info.getString(5));
				emp.setPassword(info.getString(10));
				emp.setRequests(reqdao.getAllUserRequests(emp));
				emps.add(emp);
			}
			return emps;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Request> getApprovals(Employee emp) {
		List<Request> requests = new ArrayList();
		List<Employee> emps = getEmployeesDirectlyUnder(emp.getEmployeeId());
		
		for (int i = 0; i < emps.size(); i++) {
			for (int e = 0; e < emps.get(i).getRequests().size(); e++) {
				requests.add(emps.get(i).getRequests().get(e));
			}
		}
		
		return requests;
		
	}
	
	//add an employee to the table
	public Employee insertEmployee(Employee emp) {
		try (Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String sql = "insert into employee (employee_id, username, first_name, last_name, email, password, supervisor_id)"
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEmployeeId());
			ps.setString(2, emp.getUsername());
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			ps.setString(5, emp.getEmail());
			ps.setString(6, emp.getPassword());
			ps.setInt(6, 101010);
			
			int updated = ps.executeUpdate();
			if(updated > 0) {
				System.out.println("Added " + updated + " new employee");
				return emp;
			}
			else {
				System.out.println("Wasn't added for some reason");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//get the employee title
	public String getEmployeeTitle(Employee emp) {
		try (Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String email = emp.getUsername();
			String sql = "select title from employee where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				return info.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//get supervisor id for employee
	public int getEmployeeSupervisorId(Employee emp) {
		try (Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String email = emp.getUsername();
			String sql = "select supervisor_id from employee where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				return info.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//get department ID for an employee
	public int getEmployeeDepartmentId(Employee emp) {
		try (Connection conn = ConnectionFactory
				.getInstance().getConnection();){
			String email = emp.getUsername();
			String sql = "select department_id from employee where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet info = ps.executeQuery();
			
			while(info.next()){
				return info.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}

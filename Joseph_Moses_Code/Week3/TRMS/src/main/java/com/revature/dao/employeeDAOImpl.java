package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Employee;
import com.revature.trms.util.ConnectionFactory;

public class employeeDAOImpl implements employeeDAO {

	public List<Employee> getAllEmployees() {
		List<Employee> users = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM Employee";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee temp = new Employee();
				temp.setEmpId(rs.getInt(1));
				temp.setfName(rs.getString(2));
				temp.setlName(rs.getString(3));
				temp.setEmail(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setSuperId(rs.getInt(6));
				temp.setDeptId(rs.getInt(7));
				temp.setEmpLevel(rs.getInt(8));
				temp.setEmployeeReimbursment(rs.getInt(9));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public Employee getEmployeeById(int id) {
		Employee temp = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM Employee WHERE empId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				temp.setEmpId(rs.getInt(1));
				temp.setfName(rs.getString(2));
				temp.setlName(rs.getString(3));
				temp.setEmail(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setSuperId(rs.getInt(6));
				temp.setDeptId(rs.getInt(7));
				temp.setEmpLevel(rs.getInt(8));
				temp.setEmployeeReimbursment(rs.getInt(9));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}

	public Employee getEmployeeByEmail(String email) {
		Employee temp = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM Employee WHERE email = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				temp.setEmpId(rs.getInt(1));
				temp.setfName(rs.getString(2));
				temp.setlName(rs.getString(3));
				temp.setEmail(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setSuperId(rs.getInt(6));
				temp.setDeptId(rs.getInt(7));
				temp.setEmpLevel(rs.getInt(8));
				temp.setEmployeeReimbursment(rs.getInt(9));
			}
			
		} catch (SQLException e) {
			temp.setEmpId(-1);
			return temp;
		}
		
		return temp;
	}

	public Employee addEmployee(Employee newEmp) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO Employee(fName, lName, phone, email, password, superId,"
					+ " deptId, empLevel, empReimbursmentId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			String[] key = new String[1];
			key[0] = "empId";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newEmp.getfName());
			pstmt.setString(2, newEmp.getlName());
			pstmt.setString(3, newEmp.getPhone());
			pstmt.setString(4, newEmp.getEmail());
			pstmt.setString(5, newEmp.getPassword());
			pstmt.setInt(6, newEmp.getSuperId());
			pstmt.setInt(7, newEmp.getDeptId());
			pstmt.setInt(8, newEmp.getEmpLevel());
			pstmt.setInt(9, newEmp.getEmployeeReimbursment());
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()) {
					newEmp.setEmpId(rs.getInt(1));
				}
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newEmp;
	}

}

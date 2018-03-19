package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.pojos.Employee;
import com.revature.trms.util.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {

	public List<Employee> getAllEmployees() {
		List<Employee> emps = new ArrayList<Employee>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM Employee";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee temp = new Employee();
				temp.setEmpId(rs.getInt(1));
				temp.setfName(rs.getString(2));
				temp.setlName(rs.getString(3));
				temp.setPhone(rs.getString(4));
				temp.setEmail(rs.getString(5));
				temp.setPassword(rs.getString(6));
				temp.setSuperId(rs.getInt(7));
				temp.setDeptId(rs.getInt(8));
				temp.setEmpLevel(rs.getInt(9));
				temp.setAmountAvailable(rs.getDouble(10));
				temp.setMaxAvailable(rs.getDouble(11));
				temp.setPending(rs.getDouble(12));
				temp.setAwarded(rs.getDouble(13));
				emps.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emps;
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
				temp.setPhone(rs.getString(4));
				temp.setEmail(rs.getString(5));
				temp.setPassword(rs.getString(6));
				temp.setSuperId(rs.getInt(7));
				temp.setDeptId(rs.getInt(8));
				temp.setEmpLevel(rs.getInt(9));
				temp.setAmountAvailable(rs.getDouble(10));
				temp.setMaxAvailable(rs.getDouble(11));
				temp.setPending(rs.getDouble(12));
				temp.setAwarded(rs.getDouble(13));
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
				temp.setPhone(rs.getString(4));
				temp.setEmail(rs.getString(5));
				temp.setPassword(rs.getString(6));
				temp.setSuperId(rs.getInt(7));
				temp.setDeptId(rs.getInt(8));
				temp.setEmpLevel(rs.getInt(9));
				temp.setAmountAvailable(rs.getDouble(10));
				temp.setMaxAvailable(rs.getDouble(11));
				temp.setPending(rs.getDouble(12));
				temp.setAwarded(rs.getDouble(13));
			}
			
		} catch (SQLException e) {
			
		}
		if(temp.getEmpId() == 0) return null;
		return temp;
	}

	public Employee addEmployee(Employee newEmp) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO Employee(fName, lName, phone, email, password, superId,"
					+ " deptId, empLevel, amountAvailable, maxAvailable, pending, awarded) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			pstmt.setDouble(9, newEmp.getAmountAvailable());
			pstmt.setDouble(10, newEmp.getMaxAvailable());
			pstmt.setDouble(11, newEmp.getPending());
			pstmt.setDouble(12, newEmp.getAwarded());
			
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

	@Override
	public Employee getSupervisorByName(String fName, String lName) {
		Employee temp = new Employee();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM Employee WHERE fName = ? AND lName = ? AND empLevel > 1";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fName);
			pstmt.setString(2, lName);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				temp.setEmpId(rs.getInt(1));
				temp.setfName(rs.getString(2));
				temp.setlName(rs.getString(3));
				temp.setPhone(rs.getString(4));
				temp.setEmail(rs.getString(5));
				temp.setPassword(rs.getString(6));
				temp.setSuperId(rs.getInt(7));
				temp.setDeptId(rs.getInt(8));
				temp.setEmpLevel(rs.getInt(9));
				temp.setAmountAvailable(rs.getDouble(10));
				temp.setMaxAvailable(rs.getDouble(11));
				temp.setPending(rs.getDouble(12));
				temp.setAwarded(rs.getDouble(13));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(temp.getEmpId() == 0) return null;
		return temp;
	}

	@Override
	public Employee updateEmployee(Employee updatedEmp) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE Employee SET fName = ?, lName = ?, phone = ?, email = ?, passwrod = ?, superId = ?, "
					+ "deptId = ?, empLevel = ?, amountAvailable = ?,"
					+ " maxAvailable = ?, pending = ?, awarded = ? WHERE empId = ?"; 
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedEmp.getfName());
			pstmt.setString(2, updatedEmp.getlName());
			pstmt.setString(3, updatedEmp.getPhone());
			pstmt.setString(4, updatedEmp.getEmail());
			pstmt.setString(5, updatedEmp.getPassword());
			pstmt.setInt(6, updatedEmp.getSuperId());
			pstmt.setInt(7, updatedEmp.getDeptId());
			pstmt.setInt(8, updatedEmp.getEmpLevel());
			pstmt.setDouble(9, updatedEmp.getAmountAvailable());
			pstmt.setDouble(10, updatedEmp.getMaxAvailable());
			pstmt.setDouble(11, updatedEmp.getPending());
			pstmt.setDouble(12, updatedEmp.getAwarded());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				updatedEmp = null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updatedEmp;
	}
	
	

}

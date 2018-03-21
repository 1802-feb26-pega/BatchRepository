package com.pchase95.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.project1.pojos.Employee;
import com.pchase95.project1.pojos.Reimbursment;
import com.pchase95.project1.util.ConnectionFactory;

public class EmployeeRBMTDao {
	
	static DAO<Employee> empDAO = new EmployeeDAO();
	static DAO<Reimbursment> rbmtDAO = new ReimbursmentDAO();
	
	public List<Employee> getEmployeesByRBMTId(long id) {
		List<Employee> results = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM employee_rbmt WHERE rbmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				results.add(empDAO.getById(rs.getLong(1)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public List<Reimbursment> getRBMTByEmployeeId(long id) {
		List<Reimbursment> results = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM employee_rbmt WHERE emp_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				results.add(rbmtDAO.getById(rs.getLong(2)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public boolean add(long empId, long rbmtId) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO employee_rbmt (emp_id, rbmt_id) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, empId);
			pstmt.setLong(2, rbmtId);
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean remove(long empId, long rbmtId) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM employee_rbmt WHERE emp_id = ? AND rbmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, empId);
			pstmt.setLong(2, rbmtId);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}
}

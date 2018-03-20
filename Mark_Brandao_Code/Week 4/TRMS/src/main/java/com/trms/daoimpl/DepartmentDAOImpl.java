package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trms.dao.DepartmentDAO;
import com.trms.pojos.Department;
import com.trms.util.ConnectionFactory;

public class DepartmentDAOImpl implements DepartmentDAO {

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM department ORDER BY department_name";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Department temp = new Department();
				temp.setDepartmentId(rs.getInt(1));
				temp.setDepartmentName(rs.getString(2));
				temp.setDepartmentHeadId(rs.getInt(3));
				departments.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public List<String> getAllDepartmentNames() {
		List<String> departments = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT department_name FROM department";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String temp = rs.getString(1);
				departments.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	@Override
	public String getDepartmentNameById(int deptId) {
		String department = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT department_name FROM department WHERE department_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				department = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public int getDepartmentIdByName(String deptName) {
		int departmentId = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT department_id FROM department WHERE department_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptName);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				departmentId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return departmentId;
	}

}

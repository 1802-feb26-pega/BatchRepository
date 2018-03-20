package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.Department;
import com.pchase95.util.ConnectionFactory;

public class DepartmentDAO implements DAO<Department> {
	
	@Override
	public List<Department> getAll() {
		List<Department> results = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM department";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	@Override
	public Department getById(long id) {
		Department dep = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM department WHERE dep_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dep = retrieveDepartment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dep;
	}

	@Override
	public boolean add(Department newDep) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql =
				"INSERT INTO department (dep_name) VALUES (?)";
			String[] keys = { "dep_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareDepartment(newDep, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newDep.setId(rs.getLong(1));
				}	
				conn.commit();
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(long id, Department updatedDep) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = 
					"UPDATE department SET dep_name = ? WHERE dep_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareDepartment(updatedDep, pstmt);
			pstmt.setLong(2, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	@Override
	public boolean remove(long id) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM department WHERE dep_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Department retrieveDepartment(ResultSet rs) {
		try {
			Department d = new Department();
			d.setId(rs.getLong(1));
			d.setName(rs.getString(2));
			return d;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void prepareDepartment(Department dep, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, dep.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

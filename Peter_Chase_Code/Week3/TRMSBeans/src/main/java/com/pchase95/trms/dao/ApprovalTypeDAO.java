package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.ApprovalType;
import com.pchase95.util.ConnectionFactory;

public class ApprovalTypeDAO implements DAO<ApprovalType> {

	@Override
	public List<ApprovalType> getAll() {
		List<ApprovalType> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM approval_type";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveApprovalType(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public ApprovalType getById(long id) {
		ApprovalType at = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM approval_type WHERE aprv_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				at = retrieveApprovalType(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return at;
	}

	@Override
	public boolean add(ApprovalType newApprovalType) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql =
				"INSERT INTO approval_type (aprv_status) VALUES (?)";
			String[] keys = { "aprv_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareApprovalType(newApprovalType, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newApprovalType.setId(rs.getLong(1));
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
	public boolean update(long id, ApprovalType updatedApprovalType) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = 
					"UPDATE approval_type SET aprv_status = ? WHERE aprv_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareApprovalType(updatedApprovalType, pstmt);
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
			String sql = "DELETE FROM approval_type WHERE aprv_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private ApprovalType retrieveApprovalType(ResultSet rs) {
		try {
			ApprovalType at = new ApprovalType();
			at.setId(rs.getLong(1));
			at.setStatus(rs.getString(2));
			return at;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void prepareApprovalType(ApprovalType at, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, at.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

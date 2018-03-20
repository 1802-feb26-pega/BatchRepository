package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.ReimbursmentType;
import com.pchase95.util.ConnectionFactory;

public class ReimbursmentTypeDAO implements DAO<ReimbursmentType> {

	@Override
	public List<ReimbursmentType> getAll() {
		List<ReimbursmentType> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM rbmt_type";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveReimbursmentType(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public ReimbursmentType getById(long id) {
		ReimbursmentType rt = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM rbmt_type WHERE rtype_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rt = retrieveReimbursmentType(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rt;
	}

	@Override
	public boolean add(ReimbursmentType newRT) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO rbmt_type (rtype_name, rtype_coverage) VALUES (?, ?)";
			String[] keys = { "rtype_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			prepareReimbursmentType(newRT, pstmt);

			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rowsAffected > 0) {
				while (rs.next()) {
					newRT.setId(rs.getLong(1));
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
	public boolean update(long id, ReimbursmentType updatedRT) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql =
					"UPDATE rbmt_type SET rtype_name = ?, rtype_coverage = ? WHERE rtype_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareReimbursmentType(updatedRT, pstmt);
			pstmt.setLong(3, id);
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
			String sql =
					"DELETE FROM rbmt_type WHERE rtype_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private ReimbursmentType retrieveReimbursmentType(ResultSet rs) {
		try {
			ReimbursmentType rt = new ReimbursmentType();
			rt.setId(rs.getLong(1));
			rt.setName(rs.getString(2));
			rt.setCoverage(rs.getFloat(3));
			return rt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void prepareReimbursmentType(ReimbursmentType rt, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, rt.getName());
			pstmt.setFloat(2, rt.getCoverage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

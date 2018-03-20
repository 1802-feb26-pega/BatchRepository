package com.pchase95.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.project1.pojos.GradingFormat;
import com.pchase95.project1.util.ConnectionFactory;

public class GradingFormatDAO implements DAO<GradingFormat> {

	@Override
	public List<GradingFormat> getAll() {
		List<GradingFormat> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM grading_format";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveGradingFormat(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}


	@Override
	public GradingFormat getById(long id) {
		GradingFormat gf = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM grading_format WHERE grad_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				gf = retrieveGradingFormat(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gf;
	}

	@Override
	public boolean add(GradingFormat newGradingFormat) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql =
				"INSERT INTO department (grad_name, grad_cutoff) VALUES (?, ?)";
			String[] keys = { "grad_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareGradingFormat(newGradingFormat, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newGradingFormat.setId(rs.getLong(1));
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
	public boolean update(long id, GradingFormat updatedGradingFormat) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = 
					"UPDATE grading_format SET grad_name = ?, grad_cutoff = ? WHERE dep_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareGradingFormat(updatedGradingFormat, pstmt);
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
			String sql = "DELETE FROM grading_format WHERE grad_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}
	
	private GradingFormat retrieveGradingFormat(ResultSet rs) {
		try {
			GradingFormat gf = new GradingFormat();
			gf.setId(rs.getLong(1));
			gf.setName(rs.getString(2));
			gf.setCutoff(rs.getString(3));
			
			return gf;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void prepareGradingFormat(GradingFormat gf, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, gf.getName());
			pstmt.setString(2, gf.getCutoff());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


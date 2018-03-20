package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.Attachment;
import com.pchase95.util.ConnectionFactory;

public class AttachmentDAO implements DAO<Attachment> {

	@Override
	public List<Attachment> getAll() {
		List<Attachment> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM attachment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveAttachment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public Attachment getById(long id) {
		Attachment atcmt = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM attachment WHERE atcmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				atcmt = retrieveAttachment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return atcmt;
	}

	@Override
	public boolean add(Attachment newAttachment) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql =
				"INSERT INTO attachment (rbmt_id, atcmt_data, actmt_filename) VALUES (?, ?, ?)";
			String[] keys = { "atcmt_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareAttachment(newAttachment, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newAttachment.setId(rs.getLong(1));
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
	public boolean update(long id, Attachment updatedAttachment) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = 
					"UPDATE attachment SET rbmt_id= ?, atcmt_data = ?, actmt_filename = ? WHERE dep_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareAttachment(updatedAttachment, pstmt);
			pstmt.setLong(4, id);
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
			String sql = "DELETE FROM attachment WHERE atcmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Attachment retrieveAttachment(ResultSet rs) {
		try {
			Attachment atcmt = new Attachment();
			atcmt.setId(rs.getLong(1));

			long rbmtId = rs.getLong(2);
			atcmt.setReimbursment(new ReimbursmentDAO().getById(rbmtId));

			atcmt.setData(rs.getBlob(3));
			atcmt.setFileName(rs.getString(4));

			return atcmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void prepareAttachment(Attachment atcmt, PreparedStatement pstmt) {
		try {
			pstmt.setLong(1, atcmt.getReimbursment().getId());
			pstmt.setBlob(2, atcmt.getData());
			pstmt.setString(3, atcmt.getFileName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

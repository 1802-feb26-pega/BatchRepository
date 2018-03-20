package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.Reimbursment;
import com.pchase95.util.ConnectionFactory;

public class ReimbursmentDAO implements DAO<Reimbursment> {

	@Override
	public List<Reimbursment> getAll() {
		List<Reimbursment> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM reimbursment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveReimbursment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}


	@Override
	public Reimbursment getById(long id) {
		Reimbursment rbmt = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM reimbursment WHERE rbmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				rbmt = retrieveReimbursment(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rbmt;
	}

	@Override
	public boolean add(Reimbursment newReimbursment) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql =
				"INSERT INTO reimbursment (rtype_id, emp_id, evt_id, grad_id, evt_loc_id, rbmt_event_date, rbmt_course_date, rbmt_desc, rbmt_cost, rbmt_award_amount, rbmt_submission_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = { "rbmt_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareReimbursment(newReimbursment, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newReimbursment.setId(rs.getLong(1));
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
	public boolean update(long id, Reimbursment updatedReimbursment) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = 
					"UPDATE reimbursment SET rtype_id = ?, emp_id = ?, evt_id = ?, grad_id = ?, evt_loc_id = ?, rbmt_event_date = ?, rbmt_course_date = ?, rbmt_desc = ?, rbmt_cost = ?, rbmt_award_amount = ?, rbmt_submission_date = ? WHERE rbmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareReimbursment(updatedReimbursment, pstmt);
			pstmt.setLong(12, id);
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
			String sql = "DELETE FROM reimbursment WHERE rbmt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Reimbursment retrieveReimbursment(ResultSet rs) {
		try {
			Reimbursment rbmt = new Reimbursment();
			rbmt.setId(rs.getLong(1));
			
			long rtype_id = rs.getLong(2);
			rbmt.setType(new ReimbursmentTypeDAO().getById(rtype_id));
			
			long emp_id = rs.getLong(3);
			rbmt.setApplicant(new EmployeeDAO().getById(emp_id));
			
			long evt_id = rs.getLong(4);
			rbmt.setEventType(new EventTypeDAO().getById(evt_id));
			
			long grad_id = rs.getLong(5);
			rbmt.setGradingFormat(new GradingFormatDAO().getById(grad_id));
			
			long loc_id = rs.getLong(6);
			rbmt.setEventLocation(new LocationDAO().getById(loc_id));
			
			rbmt.setEventDate(rs.getDate(7));
			rbmt.setCourseDate(rs.getDate(8));
			rbmt.setDescription(rs.getString(9));
			rbmt.setCost(rs.getDouble(10));
			rbmt.setRewardAmount(rs.getDouble(11));
			rbmt.setSubmissionDate(rs.getDate(12));
			
			return rbmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	private void prepareReimbursment(Reimbursment rbmt, PreparedStatement pstmt) {
		try {
			pstmt.setLong(1, rbmt.getType().getId());
			pstmt.setLong(2, rbmt.getApplicant().getId());
			pstmt.setLong(3, rbmt.getEventType().getId());
			pstmt.setLong(4, rbmt.getGradingFormat().getId());
			pstmt.setLong(5, rbmt.getEventLocation().getId());
			pstmt.setDate(6, rbmt.getEventDate());
			pstmt.setDate(7, rbmt.getCourseDate());
			pstmt.setString(8, rbmt.getDescription());
			pstmt.setDouble(9, rbmt.getCost());
			pstmt.setDouble(10, rbmt.getRewardAmount());
			pstmt.setDate(11, rbmt.getSubmissionDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trms.dao.ReimbursementFormDAO;
import com.trms.pojos.ReimbursementForm;
import com.trms.util.ConnectionFactory;

public class ReimbursementFormDAOImpl implements ReimbursementFormDAO {

	@Override
	public List<ReimbursementForm> getReimsByEmployeeId(int employeeId) {
		List<ReimbursementForm> reims = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM reimbursement_form WHERE employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ReimbursementForm temp = new ReimbursementForm();
				temp.setReimbursementId(rs.getInt(1));
				temp.setEmployeeId(rs.getInt(2));
				temp.setEventId(rs.getInt(3));
				temp.setDateSubmitted(rs.getDate(4));
				temp.setTimeSubmitted(rs.getTimestamp(5));
				temp.setJustification(rs.getString(6));
				temp.setGradeFormatId(rs.getInt(7));
				temp.setStatusId(rs.getInt(8));
				temp.setTimeMissed(rs.getInt(9));
				temp.setTotalCost(rs.getDouble(10));
				temp.setProjectedReimbursement(rs.getDouble(11));
				int urgent = rs.getInt(13);
				if(urgent == 0) {
					temp.setUrgent(false);
				}
				if(urgent == 1) {
					temp.setUrgent(true);
				}
				int exceedsValue = rs.getInt(14);
				if(exceedsValue == 0) {
					temp.setExceedsValue(false);
				}
				if(exceedsValue == 1) {
					temp.setExceedsValue(true);
				}
				
				reims.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reims;
	}

	@Override
	public List<ReimbursementForm> getReimsBySupervisees(int supervisorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementForm> getReimsByDept(int deptHeadId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementForm> getReimsForBenCo(int bencoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementForm addReim(ReimbursementForm rf) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO reimbursement_form VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] key = new String[1];
			key[0] = "reimbursement_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, rf.getEmployeeId());
			pstmt.setInt(2, rf.getEventId());
			pstmt.setDate(3, rf.getDateSubmitted());
			pstmt.setTimestamp(4, rf.getTimeSubmitted());
			pstmt.setString(5, rf.getJustification());
			pstmt.setInt(6,  rf.getGradeFormatId());
			pstmt.setInt(7,  rf.getStatusId());
			pstmt.setInt(8,  rf.getTimeMissed());
			pstmt.setDouble(9, rf.getTotalCost());
			pstmt.setDouble(10, rf.getProjectedReimbursement());
			int urgent = rf.isUrgent()? 1:0;
			int exceedsvalue = rf.isExceedsValue()? 1:0;
			pstmt.setInt(11, urgent);
			pstmt.setInt(12, exceedsvalue);

			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rowsAffected > 0) {
					rf.setReimbursementId(rs.getInt(1));
				}
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rf;
	}

	@Override
	public ReimbursementForm updateReim(int rfid, ReimbursementForm rf) {
		// TODO Auto-generated method stub
		return null;
	}

}

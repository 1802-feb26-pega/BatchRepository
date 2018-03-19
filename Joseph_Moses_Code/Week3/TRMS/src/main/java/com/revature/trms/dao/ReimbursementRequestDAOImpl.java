package com.revature.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;
import com.revature.trms.util.ConnectionFactory;

public class ReimbursementRequestDAOImpl implements ReimbursementRequestDAO {

	@Override
	public ReimbursementRequest addReimbursementRequest(ReimbursementRequest request) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
		
		conn.setAutoCommit(false);
		
		String sql = "INSERT INTO ReimbursementRequest(empId, requestDate, startDate, location, description, typeOfEventId,"
				+ " cost, gradingFormatId, justification, workTimeMissed, expectedReimbursment, priorityId, statusId, passingGrade)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String[] key = new String[1];
		key[0] = "rrId";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, key);
		pstmt.setInt(1, request.getEmpId());
		pstmt.setDate(2, request.getRequestDate());
		pstmt.setDate(3, request.getStartDate());
		pstmt.setString(4, request.getLocation());
		pstmt.setString(5, request.getDescription());
		pstmt.setInt(6, request.getTypeOfEventId());
		pstmt.setDouble(7, request.getCost());
		pstmt.setInt(8, request.getGradingFormatId());
		pstmt.setString(9, request.getJustification());
		pstmt.setInt(10, request.getWorkTimeMissed());
		pstmt.setDouble(11, request.getExpectedReimbursment());
		pstmt.setInt(12, request.getPriorityId());
		pstmt.setInt(13, request.getStatusId());
		pstmt.setInt(14, request.getPassingGrade());
		
		int rowsAffected = pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()) {
					request.setRrId(rs.getInt(1));
				}
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return request;
	}

	@Override
	public List<ReimbursementRequest> getAllPendingRequests(Employee emp) {
		
		List<ReimbursementRequest> pendingRequests = new ArrayList<ReimbursementRequest>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "Select * FROM ReimbursementRequest WHERE empId = ?";
			
			PreparedStatement pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, emp.getEmpId());
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()){
				ReimbursementRequest temp = new ReimbursementRequest();
				temp.setRrId(rs.getInt(1));
				temp.setEmpId(rs.getInt(2));
				temp.setRequestDate(rs.getDate(3));
				temp.setStartDate(rs.getDate(4));
				temp.setLocation(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setTypeOfEventId(rs.getInt(7));
				temp.setCost(rs.getDouble(8));
				temp.setGradingFormatId(rs.getInt(9));
				temp.setJustification(rs.getString(10));
				temp.setWorkTimeMissed(rs.getInt(11));
				temp.setExpectedReimbursment(rs.getDouble(12));
				temp.setPriorityId(rs.getInt(13));
				temp.setStatusId(rs.getInt(14));
				temp.setPassingGrade(rs.getInt(15));
				System.out.println(temp.toString());
				pendingRequests.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pendingRequests;
	}

}

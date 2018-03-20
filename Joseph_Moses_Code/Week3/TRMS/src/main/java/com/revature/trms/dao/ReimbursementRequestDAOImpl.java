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

	EmployeeDAOImpl empDao = new EmployeeDAOImpl();
	EventTypeLookUpDAOImpl eventDao = new EventTypeLookUpDAOImpl();
	GradingFormatLoopUpDAOImpl gradingFormatDao = new GradingFormatLoopUpDAOImpl();
	PriorityLookUpDAOImpl priorityDao = new PriorityLookUpDAOImpl();
	StatusLookUpDAOImpl statusDao = new StatusLookUpDAOImpl();
	
	@Override
	public ReimbursementRequest addReimbursementRequest(ReimbursementRequest request) {
		System.out.println("in request dao");
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
		
		conn.setAutoCommit(false);
		
		String sql = "INSERT INTO ReimbursementRequest(empId, requestDate, startDate, location, description, typeOfEventId,"
				+ " cost, gradingFormatId, justification, workTimeMissed, expectedReimbursment, priorityId, statusId, passingGrade)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String[] key = new String[1];
		key[0] = "rrId";
		
		PreparedStatement pstmt = conn.prepareStatement(sql, key);
		pstmt.setInt(1, request.getEmp().getEmpId());
		pstmt.setDate(2, request.getRequestDate());
		pstmt.setDate(3, request.getStartDate());
		pstmt.setString(4, request.getLocation());
		pstmt.setString(5, request.getDescription());
		pstmt.setInt(6, request.getTypeOfEvent().getTypeOfEventId());
		pstmt.setDouble(7, request.getCost());
		pstmt.setInt(8, request.getGradingFormat().getGradingFormatId());
		pstmt.setString(9, request.getJustification());
		pstmt.setInt(10, request.getWorkTimeMissed());
		pstmt.setDouble(11, request.getExpectedReimbursement());
		pstmt.setInt(12, request.getPriority().getPriorityId());
		pstmt.setInt(13, request.getStatus().getStatusId());
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
		
		
		System.out.println("getting all pending requests");
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
				temp.setEmp(empDao.getEmployeeById(rs.getInt(2)));
				temp.setRequestDate(rs.getDate(3));
				temp.setStartDate(rs.getDate(4));
				temp.setLocation(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setTypeOfEvent(eventDao.getEventType(rs.getInt(7)));
				temp.setCost(rs.getDouble(8));
				temp.setGradingFormat(gradingFormatDao.getGradingFormat(rs.getInt(9)));
				temp.setJustification(rs.getString(10));
				temp.setWorkTimeMissed(rs.getInt(11));
				temp.setExpectedReimbursement(rs.getDouble(12));
				temp.setPriority(priorityDao.getPriority(rs.getInt(13)));
				temp.setStatus(statusDao.getStatus(rs.getInt(14)));
				temp.setPassingGrade(rs.getInt(15));
				pendingRequests.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pendingRequests;
	}

	@Override
	public List<ReimbursementRequest> getAllRequestsForReviewBySuper(Employee emp) {
		
		System.out.println("getting all requests for review");
		List<ReimbursementRequest> requestsForReview = new ArrayList<ReimbursementRequest>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "Select r.rrId, r.empId, r.requestDate, r.startDate, r.location, r.description, r.typeOfEventId,"
					+ " r.cost, r.gradingFormatId, r.justification, r.workTimeMissed, r.expectedReimbursment, r.priorityId,"
					+ " r.statusId, r.passingGrade"
					+ " FROM ReimbursementRequest r"
					+ " INNER JOIN Employee e"
					+ " ON r.empId = e.empId"
					+ " WHERE r.statusId = 1 AND e.deptId = ? AND e.superId = ?";
			
			PreparedStatement pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, emp.getDeptId());
			pstmt.setInt(2, emp.getEmpId());
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()){
				ReimbursementRequest temp = new ReimbursementRequest();
				temp.setRrId(rs.getInt(1));
				temp.setEmp(empDao.getEmployeeById(rs.getInt(2)));
				temp.setRequestDate(rs.getDate(3));
				temp.setStartDate(rs.getDate(4));
				temp.setLocation(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setTypeOfEvent(eventDao.getEventType(rs.getInt(7)));
				temp.setCost(rs.getDouble(8));
				temp.setGradingFormat(gradingFormatDao.getGradingFormat(rs.getInt(9)));
				temp.setJustification(rs.getString(10));
				temp.setWorkTimeMissed(rs.getInt(11));
				temp.setExpectedReimbursement(rs.getDouble(12));
				temp.setPriority(priorityDao.getPriority(rs.getInt(13)));
				temp.setStatus(statusDao.getStatus(rs.getInt(14)));
				temp.setPassingGrade(rs.getInt(15));
				requestsForReview.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return requestsForReview;
	}

	@Override
	public ReimbursementRequest getReimbursementRequestById(int id) {
		
		ReimbursementRequest temp = new ReimbursementRequest();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "Select * FROM ReimbursementRequest WHERE rrId = ?";
			
			PreparedStatement pstmt = conn.prepareCall(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()){
				
				temp.setRrId(rs.getInt(1));
				temp.setEmp(empDao.getEmployeeById(rs.getInt(2)));
				temp.setRequestDate(rs.getDate(3));
				temp.setStartDate(rs.getDate(4));
				temp.setLocation(rs.getString(5));
				temp.setDescription(rs.getString(6));
				temp.setTypeOfEvent(eventDao.getEventType(rs.getInt(7)));
				temp.setCost(rs.getDouble(8));
				temp.setGradingFormat(gradingFormatDao.getGradingFormat(rs.getInt(9)));
				temp.setJustification(rs.getString(10));
				temp.setWorkTimeMissed(rs.getInt(11));
				temp.setExpectedReimbursement(rs.getDouble(12));
				temp.setPriority(priorityDao.getPriority(rs.getInt(13)));
				temp.setStatus(statusDao.getStatus(rs.getInt(14)));
				temp.setPassingGrade(rs.getInt(15));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public ReimbursementRequest updateReimbursementRequest(ReimbursementRequest updatedRequest) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE ReimbursementRequest SET empId = ?, requestDate = ?, startDate = ?,"
					+ " location = ?, description = ?, typeOfEventId = ?, "
					+ "cost = ?, gradingFormatId = ?, justification = ?,"
					+ " workTimeMissed = ?, expectedReimbursment = ?, priorityId = ?, "
					+ "statusId = ?, passingGrade = ? WHERE rrId = ?"; 
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, updatedRequest.getEmp().getEmpId());
			pstmt.setDate(2, updatedRequest.getRequestDate());
			pstmt.setDate(3, updatedRequest.getStartDate());
			pstmt.setString(4, updatedRequest.getLocation());
			pstmt.setString(5, updatedRequest.getDescription());
			pstmt.setInt(6, updatedRequest.getTypeOfEvent().getTypeOfEventId());
			pstmt.setDouble(7, updatedRequest.getCost());
			pstmt.setInt(8, updatedRequest.getGradingFormat().getGradingFormatId());
			pstmt.setString(9, updatedRequest.getJustification());
			pstmt.setInt(10, updatedRequest.getWorkTimeMissed());
			pstmt.setDouble(11, updatedRequest.getExpectedReimbursement());
			pstmt.setInt(12, updatedRequest.getPriority().getPriorityId());
			pstmt.setInt(13, updatedRequest.getStatus().getStatusId());
			pstmt.setInt(14, updatedRequest.getPassingGrade());
			pstmt.setInt(15, updatedRequest.getRrId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				conn.commit();
			}
			else {
				updatedRequest = null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updatedRequest;
	}
}

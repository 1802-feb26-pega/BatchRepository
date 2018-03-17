package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.Approval;
import com.pchase95.trms.pojos.Employee;
import com.pchase95.util.ConnectionFactory;

public class ApprovalDAO implements DAO<Approval> {

	@Override
	public List<Approval> getAll() {
		List<Approval> results = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM approval";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveApproval(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}


	@Override
	public Approval getById(long id) {
		Approval apvl = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM approval WHERE apvl_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				apvl = retrieveApproval(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return apvl;
	}

	@Override
	public boolean add(Approval newApproval) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"INSERT INTO approval (rbmt_id, apvl_emp_id, aprv_id, apvl_approved_id, apvl_reason) VALUES (?, ?, ?, ?, ?)";
			String[] keys = { "apvl_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			prepareApproval(newApproval, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newApproval.setId(rs.getLong(1));
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
	public boolean update(long id, Approval updatedApproval) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = 
					"UPDATE approval SET rbmt_id = ?, apvl_emp_id = ?, aprv_id = ?, apvl_approved_id = ?, apvl_reason = ? WHERE apvl_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareApproval(updatedApproval, pstmt);
			pstmt.setLong(6, id);
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
			String sql = "DELETE FROM approval WHERE apvl_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private Approval retrieveApproval(ResultSet rs) {
		try {
			Approval apvl = new Approval();
			apvl.setId(rs.getLong(1));
			
			long rbmtId = rs.getLong(2);
			apvl.setReimbursment(new ReimbursmentDAO().getById(rbmtId));
			
			DAO<Employee> empDAO = new EmployeeDAO();
			long approverEmpId = rs.getLong(3);
			apvl.setApprover(empDAO.getById(approverEmpId));
			
			long typeId = rs.getLong(4);
			apvl.setType(new ApprovalTypeDAO().getById(typeId));
			
			long recipientEmpId = rs.getLong(5);
			apvl.setRecipient(empDAO.getById(recipientEmpId));
			
			apvl.setReason(rs.getString(6));
			
			return apvl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private void prepareApproval(Approval apvl, PreparedStatement pstmt) {
		try {
			pstmt.setLong(1, apvl.getReimbursment().getId());
			pstmt.setLong(2, apvl.getApprover().getId());
			pstmt.setLong(3, apvl.getType().getId());
			pstmt.setLong(4, apvl.getRecipient().getId());
			pstmt.setString(5, apvl.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}

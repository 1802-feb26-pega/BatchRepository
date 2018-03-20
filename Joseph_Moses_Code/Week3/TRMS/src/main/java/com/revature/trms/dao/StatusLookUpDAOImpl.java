package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojos.Status;

import com.revature.trms.util.ConnectionFactory;

public class StatusLookUpDAOImpl implements StatusLookUpDAO {

	@Override
	public Status getStatus(int id) {
		Status status = new Status();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM StatusLookUp WHERE statusId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				status.setStatusId(rs.getInt(1));
				status.setStatus(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

}

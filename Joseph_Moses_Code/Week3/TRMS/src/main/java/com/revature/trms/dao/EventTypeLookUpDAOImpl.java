package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.util.ConnectionFactory;

public class EventTypeLookUpDAOImpl implements EventTypeLookUpDAO {

	@Override
	public int getCoverageById(int id) {
		int coverage = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT coverage FROM EventTypeLookUp WHERE typeId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			coverage = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return coverage;
	}

}

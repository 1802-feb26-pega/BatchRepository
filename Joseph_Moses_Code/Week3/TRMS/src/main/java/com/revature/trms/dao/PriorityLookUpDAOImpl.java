package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojos.Priority;
import com.revature.trms.util.ConnectionFactory;

public class PriorityLookUpDAOImpl implements PriorityLookUpDAO {

	@Override
	public Priority getPriority(int id) {
		
		Priority priority = new Priority();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM PriorityLookUp WHERE priorityId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				priority.setPriorityId(rs.getInt(1));
				priority.setPriority(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return priority;
	}

}

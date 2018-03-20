package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.pojos.TypeOfEvent;
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

	@Override
	public TypeOfEvent getEventType(int id) {
		TypeOfEvent event = new TypeOfEvent();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM EventTypeLookUp WHERE typeId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				event.setTypeOfEventId(rs.getInt(1));
				event.setTypeOfEvent(rs.getString(2));
				event.setCoverage(rs.getInt(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return event;
	}

}

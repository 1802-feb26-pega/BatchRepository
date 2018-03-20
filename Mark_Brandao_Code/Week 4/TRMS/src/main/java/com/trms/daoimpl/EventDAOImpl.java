package com.trms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trms.dao.EventDAO;
import com.trms.pojos.Event;
import com.trms.util.ConnectionFactory;

public class EventDAOImpl implements EventDAO {

	@Override
	public Event addEvent(Event e) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO event VALUES (?, ?, ?, ?, ?, ?)";
			String[] key = new String[1];
			key[0] = "event_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, e.getEventId());
			pstmt.setInt(2, e.getEventCoverageId());
			pstmt.setString(3, e.getDescription());
			pstmt.setString(4, e.getEventLocation());
			pstmt.setDate(5, e.getEventDate());
			pstmt.setTimestamp(6, e.getEventTime());
			int rowsAffected = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rowsAffected > 0) {
				while(rs.next()) {
					e.setEventId(rs.getInt(1));
				}
				conn.commit();
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return e;
	}

	@Override
	public Event getEventById(int eventId) {
		Event e = new Event();
		e.setEventId(eventId);
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM event WHERE event_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eventId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				e.setEventId(rs.getInt(1));
				e.setEventCoverageId(rs.getInt(2));
				e.setEventLocation(rs.getString(3));
				e.setDescription(rs.getString(4));
				e.setEventDate(rs.getDate(5));
				e.setEventTime(rs.getTimestamp(6));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return e;
	}

}

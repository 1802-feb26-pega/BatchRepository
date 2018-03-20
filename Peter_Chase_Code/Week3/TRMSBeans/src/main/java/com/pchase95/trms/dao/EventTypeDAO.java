package com.pchase95.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.trms.pojos.EventType;
import com.pchase95.util.ConnectionFactory;

public class EventTypeDAO implements DAO<EventType> {

	@Override
	public List<EventType> getAll() {
		List<EventType> results = new LinkedList<>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM event_type";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				results.add(retrieveEventType(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	@Override
	public EventType getById(long id) {
		EventType evt = null;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM event_type WHERE evt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				evt = retrieveEventType(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return evt;
	}

	@Override
	public boolean add(EventType newEventType) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"INSERT INTO event_type (evt_name) VALUES (?)";
			String[] keys = { "evt_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);

			prepareEventType(newEventType, pstmt);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newEventType.setId(rs.getLong(1));
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
	public boolean update(long id, EventType updatedEventType) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = 
					"UPDATE event_type SET evt_name = ? WHERE evt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			prepareEventType(updatedEventType, pstmt);
			pstmt.setLong(2, id);
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
			String sql = "DELETE FROM event_type WHERE evt_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	private EventType retrieveEventType(ResultSet rs) {
		try {
			EventType evt = new EventType();
			evt.setId(rs.getLong(1));
			evt.setName(rs.getString(2));
			return evt;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void prepareEventType(EventType evt, PreparedStatement pstmt) {
		try {
			pstmt.setString(1, evt.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

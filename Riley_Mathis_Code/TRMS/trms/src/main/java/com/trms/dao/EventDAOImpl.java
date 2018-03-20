package com.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;
import com.trms.util.ConnectionFactory;

public class EventDAOImpl implements EventDAO {

	@Override
	public Event addEvent(Timestamp dateScheduled, String eventLocation, int eventCost, int eventTypeId, int employeeId) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into event "
					+ "(date_scheduled, event_location, event_cost, event_type_id, employee_id) "
					+ "values(?, ?, ?, ?, ? )";
			String [] key = new String[1];
			key[0] = "event_id";
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setTimestamp(1, dateScheduled);
			ps.setString(2, eventLocation);
			ps.setInt(3, eventCost);
			ps.setInt(4, eventTypeId);
			ps.setInt(5, employeeId);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			while(pk.next()){
				id = pk.getInt(1);
			}

			conn.commit();
			Event event = new Event(dateScheduled, eventLocation, eventCost, eventTypeId, employeeId);
			event.setEventId(id);
			return event;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


//	@Override
//	public Event getEventById(int eventId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	
}

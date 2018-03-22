package com.trms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;
import com.trms.util.ConnectionFactory;

public class EventDAOImpl implements EventDAO {

	@Override
	public Event addEvent(Timestamp date, String location, int cost, int typeId, int employeeId) {
		try(Connection conn  = ConnectionFactory
				.getInstance().getConnection();){
			conn.setAutoCommit(false);

			String sql = "insert into event "
					   + "(date_scheduled, event_location, event_cost, event_type_id, employee_id) "
					   + "values(?, ?, ?, ?, ? )";
			String [] key = new String[1];
			key[0] = "event_id";
			System.out.println("got to addEvent:");
			PreparedStatement ps = conn.prepareStatement(sql, key);
			ps.setTimestamp(1, date);
			System.out.println("date: " + date);
			ps.setString(2, location);
			System.out.println("location: " + location);
			ps.setInt(3, cost);
			System.out.println("cost: " + cost);
			ps.setInt(4, typeId);
			System.out.println("type id: " + typeId);
			if (employeeId == 0) {
				employeeId = 1; //hacky fix so things don't break
			}
			ps.setInt(5, employeeId);
			System.out.println("employee id: " + employeeId);

			ps.executeUpdate();
			int id = 0;
			ResultSet pk = ps.getGeneratedKeys();
			
			while(pk.next()){
				id = pk.getInt(1);
			}

			conn.commit();
			Event event = new Event(date, location, cost, id, employeeId);
			event.setEventId(id);
			return event;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	@Override
	//(Date dateScheduled, String eventLocation, int eventCost, int eventTypeId, int employeeId
	//********* STATEMENT *********
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM event";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Event temp = new Event();
				temp.setDateScheduled(rs.getTimestamp(1));
				temp.setEventLocation(rs.getString(2));
				temp.setEventCost(rs.getInt(3));
				temp.setEventTypeId(rs.getInt(4));
				temp.setEmployeeId(rs.getInt(5));
				events.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	@Override
	//********* CALLABLE STATEMENT *********
	public Double getTotalReimbursements(int employeeId) {
		double output = 0.0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{call get_reimbursements_total(?, ?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, employeeId);
			cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
			
			cstmt.executeQuery();
			
			output = cstmt.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	@Override
    public ArrayList<Event> getEventsByUser(Employee employee) {
		//TODO
    	return null;
    }
}

package com.revature.trms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.revature.trms.util.ConnectionFactory;

public class EventDAOImpl implements EventDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	@Override
	public Collection<String> getAllEventTypes()
	{
		Collection<String> allEvents = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT eventtype FROM events";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				allEvents.add(rs.getString(1));
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allEvents;
	}

	@Override
	public Double getEventReimbursement(String event)
	{
		Double reimburse = 0.00;
		Integer transfer = 0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT reimbursement FROM events WHERE eventtype = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				transfer = rs.getInt(1);
			}
			
			reimburse = (double) transfer / 100;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return reimburse;
	}

}

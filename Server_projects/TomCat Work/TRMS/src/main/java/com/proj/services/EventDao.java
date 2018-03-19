package com.proj.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.proj.util.ConnectionFactory;

public class EventDao {
	ArrayList<String> event = new ArrayList<>();


	public ArrayList<String> getEvents(){

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT TYPE FROM EVENT_TYPE";

			Statement statement = conn.prepareStatement(sql);


			ResultSet crs = statement.executeQuery(sql);

			while(crs.next()) {
				event.add(crs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return event;
	}
}




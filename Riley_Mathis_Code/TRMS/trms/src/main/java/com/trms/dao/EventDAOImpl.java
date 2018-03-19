package com.trms.dao;

import java.util.ArrayList;

import com.trms.pojos.Event;

public class EventDAOImpl implements EventDAO {

//	@Override
//	public Event getEventById(int eventId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ArrayList<Event> getEventsByUser(Employee employee) {
//		ArrayList<Event> accounts = new ArrayList<Event>();
//
//		try(Connection conn = ConnectionFactory
//				.getInstance().getConnection();){
//			String sql = "select * from accounts where user_id = ?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, u.getId());
//			ResultSet info = ps.executeQuery();
//
//			while(info.next()){
//				Account temp = new Account();
//				temp.setId(info.getInt(1));
//				System.out.println("Account " + temp.getId());
//				temp.setBalance(info.getDouble(3));
//				temp.setUser(u);
//				accounts.add(temp);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//
//
//
//		return accounts;
//	}
//
//	
//

}

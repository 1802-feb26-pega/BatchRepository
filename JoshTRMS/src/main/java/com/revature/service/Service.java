package com.revature.service;

import java.sql.SQLException;

import com.revature.backend.User;
import com.revature.data.UserDAO;
import com.revature.trms.ConnectionFactory;
public class Service {
		
		static UserDAO dao = new UserDAO(ConnectionFactory.getInstance().getConnection());
		
		public User login(String username, String password) {
			User user;
			user = null;
			try {
				user = dao.getUserLoginInfo(username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user== null) return null;
			else if (user.getPassword().equals(password)) {
				return user;
			}
			else return null;
		}
		
		/*public User addUser(User u) {
			return dao.
		
		public boolean emailExists(String email) {
			User u;
			try {
				u = dao.getUserLoginInfo(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(u == null) return false;
			else return true;
		}
		
	
*/
	}


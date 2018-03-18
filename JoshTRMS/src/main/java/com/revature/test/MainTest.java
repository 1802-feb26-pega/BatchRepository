package com.revature.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.data.UserDAO;
import com.revature.trms.ConnectionFactory;

public class MainTest {


	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		//Test UserDAO
		UserDAO userDao = new UserDAO(conn);
		
	
		userDao.getAll();
	
		conn.close();
		System.out.println("Finished");

	}

}
	
	


package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.trms.ConnectionFactory;

public class ReimbDAOImpl {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// get connection
		Connection conn = ConnectionFactory.getInstance().getConnection();
		
		// Test ReimbursementDAO
		ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
		
	
		reimbDao.getReimbByStatus("Approved");
		
	
		conn.close();
		System.out.println("Finished");
	}
}

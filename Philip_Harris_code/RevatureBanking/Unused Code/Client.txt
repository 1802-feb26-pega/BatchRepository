package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.ConnectionFactory;


public class Client_Dao{

	public static int writeClient(Client c) {
		int ra = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "INSERT INTO CUSTOMER (Firstname,Lastname,Social,Username,PASS) VALUES (?,?,?,?,?)";
			String[] key = new String[1];
			key[0] = "U_id";

			PreparedStatement p_statement = conn.prepareStatement(sql,key);
			p_statement.setString(1, c.getfName());
			p_statement.setString(2, c.getlName());
			p_statement.setInt(3, c.getSsn());
			p_statement.setString(4, c.getUsrName());
			p_statement.setString(5, c.getPassword());

			ra = p_statement.executeUpdate();
			ResultSet rs = p_statement.getGeneratedKeys();
			if(ra > 0) {
				while(rs.next()) 
					c.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ra;
	}

	public static boolean readCustomer(Client c, Account a, String usrname, String pass) {
		// TODO Auto-generated method stub
		return false;
	}
}

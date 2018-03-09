package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.ConnectionFactory;

public class Account_Dao implements Account_Interface{		
	public static int writeAccount(Account a,Client c) {
		int ra = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ACCOUNT (ACC_ID,U_ID,ACCOUNT_NUM,BALANCE,TYPE) VALUES (?,?,?,?,?)";
			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, 1);
			p_statement.setInt(2, c.getId());
			p_statement.setLong(3, a.getAccountNumber());
			p_statement.setInt(4, a.getBalance());
			p_statement.setInt(5, a.getType());

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
}


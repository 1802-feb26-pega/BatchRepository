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
			
			String sql = "INSERT INTO ACCOUNT (U_ID,ACCOUNT_NUM,BALANCE,TYPE) VALUES (?,?,?,?)";
			String[] key = new String[1];
			key[0] = "ACC_ID";
			PreparedStatement p_statement = conn.prepareStatement(sql,key);
			p_statement.setInt(1, c.getId());
			p_statement.setLong(2, a.getAccountNumber());
			p_statement.setInt(3, a.getBalance());
			p_statement.setInt(4, a.getType());

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


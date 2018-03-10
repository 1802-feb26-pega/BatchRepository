package com.revature.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.Validation;


public class DaoImpl implements Client_Interface, Account_Interface{
	public static boolean writeUser(Client c, Account a) {
		int ara = -1;
		int cra = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "INSERT INTO CUSTOMER (Firstname,Lastname,Social,Username,PASS) VALUES (?,?,?,?,?)";
			String[] ckey = new String[1];
			ckey[0] = "U_id";

			PreparedStatement p_statement = conn.prepareStatement(sql,ckey);
			p_statement.setString(1, c.getfName());
			p_statement.setString(2, c.getlName());
			p_statement.setInt(3, c.getSsn());
			p_statement.setString(4, c.getUsrName());
			p_statement.setString(5, c.getPassword());

			cra = p_statement.executeUpdate();
			ResultSet crs = p_statement.getGeneratedKeys();
			if(cra > 0) {
				while(crs.next()) 
					c.setId(crs.getInt(1));
			}


			sql = "INSERT INTO ACCOUNT (U_ID,ACCOUNT_NUM,BALANCE,TYPE) VALUES (?,?,?,?)";
			String[] akey = new String[1];
			akey[0] = "ACC_ID";
			p_statement = conn.prepareStatement(sql,akey);
			p_statement.setInt(1, c.getId());
			p_statement.setLong(2, a.getAccountNumber());
			p_statement.setInt(3, a.getBalance());
			p_statement.setInt(4, a.getType());

			ara = p_statement.executeUpdate();
			ResultSet ars = p_statement.getGeneratedKeys();
			if(ara > 0) {
				while(ars.next()) 
					c.setId(ars.getInt(1));
			}

			if(Validation.checking_signup(cra, ara)){
				conn.commit();
				return true;
			}else {
				conn.rollback();
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void readCustomer(Client c, String usrname, String pass) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT U_ID,FIRSTNAME, LASTNAME,"
					+ "SOCIAL, USERNAME, PASS FROM CUSTOMER WHERE USERNAME = ? AND PASS = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setString(1, usrname);
			p_statement.setString(2, pass);

			ResultSet crs = p_statement.executeQuery();

			while(crs.next()) {
				c.setId(crs.getInt(1));
				c.setfName(crs.getString(2));
				c.setlName(crs.getString(3));
				c.setSsn(crs.getInt(4));
				c.setUsrName(crs.getString(5));
				c.setPassword(crs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}

	public static void readAccount(Client c, Account a) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT ACCOUNT_NUM, BALANCE, TYPE FROM ACCOUNT WHERE U_ID = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, c.getId());
			p_statement.executeQuery();


			ResultSet crs = p_statement.executeQuery();

			while(crs.next()) {
				a.setAccountNumber(crs.getLong(1));
				a.setBalance(crs.getInt(2));
				a.setId(crs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}
}

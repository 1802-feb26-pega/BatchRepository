package com.revature.banking.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.Validation;


public class DaoImpl implements Client_Interface, Account_Interface{
	public boolean writeUser(Client c, Account a) {
		int ara = -1;
		int cra = -1;
		//Creates an instance of the connector for the JDBC
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			//Set to commit to avoid descynchro of account and client
			conn.setAutoCommit(false);
			
			//SQL Query that adds new customer to table
			String sql = "INSERT INTO CUSTOMER (Firstname,Lastname,Social,Username,PASS) VALUES (?,?,?,?,?)";
			
			String[] ckey = new String[1];
			ckey[0] = "U_id";

			//Using prepared statements to avoid SQL injection
			PreparedStatement p_statement = conn.prepareStatement(sql,ckey);
			p_statement.setString(1, c.getfName());
			p_statement.setString(2, c.getlName());
			p_statement.setInt(3, Validation.eROT5(c.getSsn())); //Hashes SSN
			p_statement.setString(4, c.getUsrName());
			p_statement.setString(5, Validation.encode(c.getPassword()));//Hashes password
			
			//Run SQL statement
			cra = p_statement.executeUpdate();
			//Return back primary key for future use
			ResultSet crs = p_statement.getGeneratedKeys();
			if(cra > 0) {
				while(crs.next()) 
					c.setId(crs.getInt(1));
			}


			//SQL Code that allows account to be inserted into DB
			sql = "INSERT INTO ACCOUNT (U_ID,ACCOUNT_NUM,BALANCE,ACCOUNT_TYPE) VALUES (?,?,?,?)";
			String[] akey = new String[1];
			akey[0] = "ACC_ID";
			
			
			p_statement = conn.prepareStatement(sql,akey);
			p_statement.setInt(1, c.getId());
			p_statement.setLong(2, a.getAccountNumber());
			p_statement.setInt(3, a.getBalance());
			p_statement.setInt(4, a.getType());

			//Run SQL 
			ara = p_statement.executeUpdate();
			ResultSet ars = p_statement.getGeneratedKeys();
			
			//Grabs primary key for future use
			if(ara > 0) {
				while(ars.next()) 
					c.setId(ars.getInt(1));
			}

			//Code that double checks that both tables were updated
			//if a table was not updated then rollback;
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

	//Reads the customer from the database
	//Uses similiar code to the write user
	public void readCustomer(Client c, String usrname, String pass) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT U_ID,FIRSTNAME, LASTNAME,"
					+ "SOCIAL, USERNAME, PASS FROM CUSTOMER WHERE USERNAME = ? AND PASS = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setString(1, usrname);
			p_statement.setString(2, Validation.encode(pass));

			ResultSet crs = p_statement.executeQuery();

			while(crs.next()) {
				c.setId(crs.getInt(1));
				c.setfName(crs.getString(2));
				c.setlName(crs.getString(3));
				c.setSsn(Validation.dROT5(crs.getInt(4)));
				c.setUsrName(crs.getString(5));
				c.setPassword(Validation.decode(crs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}

	//Reads the account from the database
	//Uses similiar code to the read user
	public void readAccount(Client c, Account a) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "SELECT ACC_ID, ACCOUNT_NUM, BALANCE, ACCOUNT_TYPE FROM ACCOUNT WHERE U_ID = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, Client.getId());

			ResultSet crs = p_statement.executeQuery();

			while(crs.next()) {
				a.setId(crs.getInt(1));
				a.setAccountNumber(crs.getLong(2));
				a.setBalance(crs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
	}

	//Method that takes the new balance and updates it to the DB
	public boolean updateBalance(Account a) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "UPDATE ACCOUNT SET balance = ? WHERE ACC_ID = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, a.getBalance());
			p_statement.setInt(2, a.getId());

			int check = p_statement.executeUpdate();	

			if(Validation.check_update(check)){
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

	//Creates a new account on the DB
	public boolean createAcc(Account a) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "INSERT INTO ACCOUNT (U_ID,ACCOUNT_NUM,BALANCE,ACCOUNT_TYPE) VALUES (?,?,?,?)";

			PreparedStatement p_statement = conn.prepareStatement(sql);

			p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, Client.getId());
			p_statement.setLong(2, a.getAccountNumber());
			p_statement.setInt(3, a.getBalance());
			p_statement.setInt(4, a.getType());

			int result = p_statement.executeUpdate();	

			if(Validation.check_update(result)){
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

	//Grabs a list of accounts that belong to the user
	//Used normal statements since it was just a SELECT query
	public List<Account> getAccounts() {
		List<Account> account = new ArrayList<Account>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){


			String sql = "SELECT * FROM ACCOUNT WHERE U_ID = " + Client.getId();

			Statement statement = conn.createStatement();


			ResultSet crs = statement.executeQuery(sql);

			while(crs.next()) {
				Account acc = new Account();
				acc.setId(crs.getInt(1));
				acc.setAccountNumber(crs.getInt(3));
				acc.setBalance(crs.getInt(4));
				acc.setType(crs.getInt(5));
				account.add(acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}

		return account;

	}
	
	//Delete account removes an account from users view
	//Ues a prodcedure to delete code and called by callable
	public boolean d_account(Account account) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "{CALL DEL_ACC(?)}";

			CallableStatement c_statement = conn.prepareCall(sql);

			c_statement.setInt(1, account.getId());

			int result = c_statement.executeUpdate();	

			if(Validation.check_update(result)){
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

	//Grabs a list of usernames from the DB
	//Also used statments since it was a SELECT query
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		List<Client>  list = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "SELECT USERNAME FROM CUSTOMER";

			Statement statement = conn.createStatement();


			ResultSet crs = statement.executeQuery(sql);

			while(crs.next()) {
				Client old = new Client();
				old.setUsrName(crs.getString(1));
				list.add(old);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		return list;
	}
}

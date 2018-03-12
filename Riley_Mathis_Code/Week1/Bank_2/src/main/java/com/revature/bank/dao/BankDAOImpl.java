package com.revature.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.Customer;
import com.revature.bank.util.ConnectionFactory;

public class BankDAOImpl implements BankDAO{

	public Customer getCustomerByUsername(String username) {
		Customer customer = new Customer();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM customer WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			customer.setId(rs.getInt(1));
			customer.setFirstname(rs.getString(2));
			customer.setLastname(rs.getString(3));
			customer.setUsername(rs.getString(4));
			customer.setPassword(rs.getString(5));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There was a problem processing your request!");
		}
		return customer;
	}
	
	public Customer getCustomerById(int id) {
		Customer customer = new Customer();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM customer WHERE customerid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			customer.setId(rs.getInt(1));
			customer.setFirstname(rs.getString(2));
			customer.setLastname(rs.getString(3));
			customer.setUsername(rs.getString(4));
			customer.setPassword(rs.getString(5));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}

	public Account getAccountById(int id) {
		Account account = new Account();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM account WHERE accountid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			account.setId(rs.getInt(1));
			account.setCustomerId(rs.getInt(2));
			account.setBalance(rs.getInt(3));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}

	public Customer registerCustomer(Customer customer) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO customer(firstname,lastname,username,pwd)"
					+ " VALUES (?,?,?,?)";
			String[] key = new String[5];
			key[0] = "customerid";
			key[1] = "firstname";
			key[2] = "lastname";
			key[3] = "username";
			key[4] = "pwd";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, customer.getFirstname());
			pstmt.setString(2, customer.getLastname());
			pstmt.setString(3, customer.getUsername());
			pstmt.setString(4, customer.getPassword());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if(rowsAffected > 0) {
				while(rs.next()) {
					customer.setId(rs.getInt(1));
					customer.setFirstname(rs.getString(2));
					customer.setLastname(rs.getString(3));
					customer.setUsername(rs.getString(4));
					customer.setPassword(rs.getString(5));
				}
				conn.commit();
				System.out.println("Thank you for registering!");
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("Registration failure. That username already exists!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public Account registerAccount(Account account) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO account(customerid)"
					+ " VALUES (?)";
			String[] key = new String[3];
			key[0] = "accountid";
			key[1] = "customerid";
			key[2] = "balance";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, account.getCustomerId());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if(rowsAffected > 0) {
				while(rs.next()) {
					account.setId(rs.getInt(1));
					account.setCustomerId(rs.getInt(2));
					account.setBalance(rs.getInt(3));

				}
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}


	public Account updateBalance(Account account, int num) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "UPDATE account SET balance = ? WHERE accountid = ?";
			String[] key = new String[3];
			key[0] = "accountid";
			key[1] = "customerid";
			key[2] = "balance";
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, num);
			pstmt.setInt(2, account.getId());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rowsAffected > 0) {
				while(rs.next()) {
					account.setId(rs.getInt(1));
					account.setCustomerId(rs.getInt(2));
					account.setBalance(rs.getInt(3));
				}
				conn.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	public int balance(Account account) {
		// TODO Auto-generated method stub
		int balance = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{CALL showBalance(?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, account.getId());
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			int value = cstmt.executeUpdate();
			//rs.next();
			balance = cstmt.getInt(2);
			if(value < 1) {
				System.out.println("Couldn't get balance for that account!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Customer temp = new Customer();
				temp.setId(rs.getInt(1));
				temp.setFirstname(rs.getString(2));
				temp.setLastname(rs.getString(3));
				temp.setUsername(rs.getString(4));
				temp.setPassword(rs.getString(5));
				customers.add(temp);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	
}

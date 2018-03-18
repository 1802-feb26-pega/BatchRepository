package com.trms.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

import com.trms.pojos.User;
import com.trms.util.ConnectionFactory;

public class DAOImpl implements DAO
{

	//	//chris
	//	@Override
	//	public User getUserByUsernameAndPass(String uname, String pwd)
	//	{
	//		User u = new User();
	//		
	//		try(Connection conn = ConnectionFactory.getInstance().getConnection())
	//		{
	//			String sql = "SELECT * FROM users WHERE email=? AND password=?";
	//			
	//			PreparedStatement ps = conn.prepareStatement(sql);
	//			ps.setString(1, uname);
	//			ps.setString(2, pwd);
	//			ResultSet rs = ps.executeQuery();
	//			
	//			while(rs.next())
	//			{
	//				u.setId(rs.getInt(1));
	//				u.setFirstname(rs.getString(2));
	//				u.setLastname(rs.getString(3));
	//				u.setEmail(rs.getString(4));
	//				u.setPassword(rs.getString(5));
	//			}
	//			
	//		} catch (SQLException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		
	//		if(u.getId()==0)
	//		{
	//			return null;
	//		}
	//		
	//		
	//		return u;
	//	}
	//	
	//	
	//	//chris
	//	@Override
	//	public List<User> getAllUsers()
	//	{
	//		List<User> users = new ArrayList<User>();
	//		User u = new User();
	//		
	//		try(Connection conn = ConnectionFactory.getInstance().getConnection())
	//		{
	//			String sql = "SELECT * FROM users";
	//			Statement s = conn.createStatement();
	//			ResultSet rs = s.executeQuery(sql);
	//
	//			while(rs.next())
	//			{
	//				u.setId(rs.getInt(1));
	//				u.setFirstname(rs.getString(2));
	//				u.setLastname(rs.getString(3));
	//				u.setEmail(rs.getString(4));
	//				u.setPassword(rs.getString(5));
	//			}
	//		} catch (SQLException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		
	//		return users;
	//	}
	//
	//	@Override
	//	public User getUserById(int id) {
	//		User u = new User();
	//		try(Connection conn = ConnectionFactory
	//				.getInstance().getConnection();){
	//			String sql = "select * from users where u_id =  ?";
	//			PreparedStatement ps = conn.prepareStatement(sql);
	//			ps.setInt(1, id);
	//			ResultSet info = ps.executeQuery();
	//
	//			while(info.next()){
	//				u.setId(info.getInt(1));
	//				u.setFirstname(info.getString(2));
	//				u.setLastname(info.getString(3));
	//				u.setEmail(info.getString(4));
	//				u.setPassword(info.getString(5));
	//			}
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}
	//
	//		return u;
	//	}
	//

	//
	//	@Override
	//	public Account createAccount(User u, double initialBal) {
	//		Account a = new Account();
	//
	//		try(Connection conn  = ConnectionFactory
	//				.getInstance().getConnection();){
	//			conn.setAutoCommit(false);
	//
	//			String sql = "insert into accounts(user_id, balance)"
	//					+ " values (?,?)";
	//			String[] key = new String[1];
	//			key[0] = "acc_id";
	//			PreparedStatement ps = conn.prepareStatement(sql, key);
	//			ps.setInt(1, u.getId());
	//			ps.setDouble(2, initialBal);
	//
	//			ps.executeUpdate();
	//			int id = 0;
	//			ResultSet pk = ps.getGeneratedKeys();
	//			while(pk.next()){
	//				id = pk.getInt(1);
	//			}
	//			System.out.println("id is " + id);
	//			a.setId(id);
	//			a.setBalance(initialBal);
	//			//	a.setType();
	//
	//			conn.commit();
	//
	//
	//
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		}
	//
	//
	//
	//		return a;
	//	}
	//
	//	@Override
	//	public ArrayList<Account> getAccountsByUser(User u) {
	//		ArrayList<Account> accounts = new ArrayList<Account>();
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
	//				temp.setBalance(info.getDouble(3));
	//				temp.setUser(u);
	//				accounts.add(temp);
	//				System.out.println("Account " + temp.getId() + " = $"+temp.getBalance());
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
	//	@Override
	//	public double getBalance(int id) {
	//		double bal = 0.0;
	//		try(Connection conn = ConnectionFactory
	//				.getInstance().getConnection();){
	//			String sql = "select balance from accounts where acc_id = ?";
	//			PreparedStatement ps = conn.prepareStatement(sql);
	//			ps.setInt(1, id);
	//			ResultSet info = ps.executeQuery();
	//
	//			while(info.next()){
	//				bal = info.getDouble(1);
	//			}
	//		}
	//		catch(SQLException e){
	//			e.printStackTrace();
	//		}
	//		return bal;
	//	}
	//
	//	@Override
	//	public void updateBalance(int id, double amt) {
	//		try(Connection conn = ConnectionFactory
	//				.getInstance().getConnection();){
	//			String sql = "update accounts set balance = ? where acc_id=?";
	//			PreparedStatement ps = conn.prepareStatement(sql);
	//			ps.setDouble(1, amt );
	//			ps.setInt(2, id);
	//			ps.executeUpdate();
	//		}
	//		catch(SQLException e){
	//			e.printStackTrace();
	//		}
	//
	//	}

	@Override
	public User getUserByUsername(String username) {
		User u = new User();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			username = username.toLowerCase();
			String sql = "SELECT * FROM employee WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet temp = ps.executeQuery();

			while(temp.next()){
				u.setId(temp.getInt(1));
				u.setFirstname(temp.getString(2));
				u.setLastname(temp.getString(3));
				u.setUsername(temp.getString(4));
				u.setPassword(temp.getString(5));
				//u.setBirthdate(temp.getString(6));
				u.setAddress(temp.getString(7));
				u.setZipcode(temp.getString(8));
				u.setTitle(temp.getString(9));
				u.setSupervisorId(temp.getInt(10));
				u.setDeptHeadId(temp.getInt(11));
				u.setBenCoId(temp.getInt(12));
				u.setApprovedFunds(temp.getDouble(13));
				u.setPendingFunds(temp.getDouble(14));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(u.getId()==0)
		{
			//System.out.println("daoimpl get user returns null");
			return null;
		}

		return u;
	}//getUserByUsername

	//========================================================================

	@Override
	public int addUser(String fn,String ln,String un,String pass,String addr,String zip,String title,int sup,int head,int ben,double app,double pen)
	{
		String passHash = null;
		MessageDigest digest;
		int value=-1;

		try
		{//first, hash the password entered by the user
			digest = MessageDigest.getInstance("SHA-512");
			byte[] encodedHash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < encodedHash.length; i++)
			{
				String hex = Integer.toHexString(0xff & encodedHash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			passHash = hexString.toString();	//pass hash is stored here
			//System.out.println(passHash);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		//second, call the stored procedure stored in db that creates a new user/employee
		try(Connection conn  = ConnectionFactory.getInstance().getConnection();)
		{
			//			conn.setAutoCommit(false);
			//
			//			//				String sql = "insert into users "
			//			//						+ "(firstname, lastname, email, password) "
			//			//						+ "values(?, ?, ?, ? )";
			//
			//			String sql = "INSERT INTO USERS (firstname,lastname,email,password) VALUES(?,?,?,?)";
			//			String [] key = new String[1];
			//			key[0] = "u_id";
			//			PreparedStatement ps = conn.prepareStatement(sql, key);
			//			ps.setString(1, fn);
			//			ps.setString(2, ln);
			//			ps.setString(3, email);
			//			ps.setString(4, passHash);
			//
			//			ps.executeUpdate();
			//			int id = 0;
			//			ResultSet pk = ps.getGeneratedKeys();
			//			while(pk.next()){
			//				id = pk.getInt(1);
			//			}
			//
			//			conn.commit();
			//			User u = new User(fn, ln, email, pass);
			//			u.setId(id);

			//			public User addUser(String fn,String ln,String un,String pass,String bday,String addr,String zip,String title,int sup,int head,int ben,double app,double pen)

			String sql = "{CALL new_emp(?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement c = conn.prepareCall(sql);
			c.setString(1,fn);
			c.setString(2,ln);
			c.setString(3,un);
			c.setString(4,passHash);
			//c.setString(5,bday);
			c.setString(5,addr);
			c.setString(6,zip);
			c.setString(7,title);
			c.setInt(8,sup);		//supervisor id
			c.setInt(9,head);		//department head id
			c.setInt(10,ben);		//benco id
			c.setDouble(11,app);	//approved funds
			c.setDouble(12,pen);	//pending funds
			value = c.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		}

		return value;
	}//add user

	//=================================================


	@Override
	public int addRequest(String eType, Date sDate, Date eDate, String loc, String desc, double cost, int gStyleId,
			int grade, Date rDate, Timestamp t, int flaggedId, int appId, int uId)
	{
		int value = -1;

		try(Connection conn  = ConnectionFactory.getInstance().getConnection();)
		{
			String sql = "{CALL new_request(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement c = conn.prepareCall(sql);
			c.setString(1,eType);
			c.setDate(2,sDate);
			c.setDate(3,eDate);
			c.setString(4,loc);
			c.setString(5, desc);
			c.setDouble(6, cost);
			c.setInt(7,gStyleId);
			c.setInt(8,grade);
			c.setDate(9,rDate);
			c.setTimestamp(10, t);
			c.setInt(11, flaggedId);
			c.setInt(12, appId);
			c.setInt(13, uId);
			value = c.executeUpdate();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return value;
	}
}


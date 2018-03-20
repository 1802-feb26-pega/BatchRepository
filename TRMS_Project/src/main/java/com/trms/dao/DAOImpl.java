package com.trms.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.trms.pojos.Request;
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
				u.setDepartmentId(temp.getInt(10));
				u.setApprovedFunds(temp.getDouble(11));
				u.setPendingFunds(temp.getDouble(12));
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
	public int addUser(String fn,String ln,String un,String pass,String addr,String zip,String title,int dept,double app,double pen)
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

			String sql = "{CALL new_emp(?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement c = conn.prepareCall(sql);
			c.setString(1,fn);
			c.setString(2,ln);
			c.setString(3,un);
			c.setString(4,passHash);
			//c.setString(5,bday);
			c.setString(5,addr);
			c.setString(6,zip);
			c.setString(7,title);
			c.setInt(8,dept);
			c.setDouble(9,app);	//approved funds
			c.setDouble(10,pen);	//pending funds
			value = c.executeUpdate();

			System.out.println(value + " adduser dao");

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
	}//addrequest

	//===============================

	public List<Request> getRequestsByUser(User u)
	{
		List<Request> requests = new ArrayList<Request>();
		List<Integer> requestIds = new ArrayList<Integer>();
		//Request temp = new Request();
		int id = u.getId();


		try(Connection conn  = ConnectionFactory.getInstance().getConnection();)
		{
			//first access the junction and get a list of the req_id's that are paired to the given id
			String sql = "SELECT request_id FROM employee_request_junc WHERE emp_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();

			while(rs.next())
			{
				requestIds.add(rs.getInt(1));
			}

			//now get all requests from request table from corresponding request ids from the previous query
			//while(!requestIds.isEmpty())
			for(Integer i : requestIds)
			{
				//Integer current = requestIds.get(0);
				sql = "SELECT * FROM request WHERE request_id=?";
				p = conn.prepareStatement(sql);
				p.setInt(1, i);
				rs = p.executeQuery();

				while(rs.next())
				{	
					Request temp = new Request();
					temp.setRequestId(rs.getInt(1));
					temp.setEventType(rs.getString(2));
					temp.setStartDate(rs.getDate(3));
					temp.setEndDate(rs.getDate(4));
					temp.setLocation(rs.getString(5));
					temp.setDescription(rs.getString(6));
					temp.setCost(rs.getDouble(7));
					temp.setGradingStyleId(rs.getInt(8));
					temp.setGrade(rs.getInt(9));
					temp.setRequestDate(rs.getDate(10));
					temp.setRequestTime(rs.getTimestamp(11));
					temp.setFlaggedId(rs.getInt(12));
					temp.setApprovalId(rs.getInt(13));
					requests.add(temp);
					//System.out.println(temp.toString());
				}//while - rs
				//requestIds.remove(0);
			}//while - request Ids

		} catch(SQLException e)
		{
			e.printStackTrace();
		}//try catch
		
		return requests;
	}//getrequestsbyuser
	
	public int addApproval(int requestId)
	{
		int value = -1;
		
		try(Connection conn  = ConnectionFactory.getInstance().getConnection();)
		{
			String sql = "{call new_approval(?,?,?,?,?,?,?,?)}";
			CallableStatement c = conn.prepareCall(sql);
			c.setInt(1, requestId);
			c.setNull(2,java.sql.Types.INTEGER);
			c.setNull(3,java.sql.Types.INTEGER);
			c.setNull(4,java.sql.Types.INTEGER);
			c.setString(5,"");
			c.setString(6,"");
			c.setString(7,"");
			c.setDouble(8, 0);
			value = c.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
			
		return value;
	}//addapproval
	
	public int updateApproval(int employeeId, String status,int requestId)
	{
		int value=-1;
		String sql;
		
		try(Connection conn  = ConnectionFactory.getInstance().getConnection();)
		{
			if(employeeId>=1000 && employeeId<2000)
			{
				//supervisor
				sql = "UPDATE approval SET direct_sup_id=?,direct_sup_status=? WHERE request_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, employeeId);
				p.setString(2, status);
				p.setInt(3, requestId);
				value = p.executeUpdate();
				
			}else if(employeeId>=2000 && employeeId<3000)
			{
				//department head
				sql = "UPDATE approval SET dept_head_id=?,dept_head_status=? WHERE request_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, employeeId);
				p.setString(2, status);
				p.setInt(3, requestId);
				value = p.executeUpdate();
			}else if(employeeId>=3000 && employeeId<4000)
			{
				//benco
				sql = "UPDATE approval SET benco_id=?,benco_status=? WHERE request_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, employeeId);
				p.setString(2, status);
				p.setInt(3, requestId);
				value = p.executeUpdate();
			}
			
			sql = "COMMIT";
			Statement s = conn.createStatement();
			s.executeQuery(sql);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return value;
	}
	
}//daoimpl




























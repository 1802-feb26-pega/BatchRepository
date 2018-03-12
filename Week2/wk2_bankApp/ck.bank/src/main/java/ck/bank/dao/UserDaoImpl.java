package ck.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ck.bank.pojos.User;
import ck.bank.util.ConnectionFactory;



public class UserDaoImpl implements UserDao{
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			String sql = "SELECT * FROM customer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{	
				User temp = new User();
				//the first element will be userid -- dont know if this will be needed in the java app
				temp.setUsername(rs.getString(2));
				temp.setFirstName(rs.getString(3));
				temp.setLastName(rs.getString(4));
				temp.setPassHash(rs.getString(5));
				users.add(temp);
				
			}//while
			
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}//try-catch
		
		return users;
	}//getAllUsers
	
	
	
	public int addUser(User newUser)
	{
		int value=-1;	//default return value
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			/*
			String sql = "INSERT INTO artist VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newArtist.getId());
			pstmt.setString(3, newArtist.getName());
			value = pstmt.executeUpdate();
			*/
			
//			String sql = "EXECUTE create_new_user(?,?,?,?);";
			String sql = "{CALL create_new_user(?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1,newUser.getUsername());
			cstmt.setString(2,newUser.getFirstName());
			cstmt.setString(3,newUser.getLastName());
			cstmt.setString(4,newUser.getPassHash());
			value = cstmt.executeUpdate();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//addUser
	
	//================================================================================
	
	public User getUserByUsername(String name)
	{
		User result = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "SELECT * FROM customer WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next())
			{
				result.setUsername(rs.getString(1));
				result.setFirstName(rs.getString(2));
				result.setLastName(rs.getString(3));
				result.setPassHash(rs.getString(4));
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	//================================================================================
	
	public int updateUsername(String newU,String old)
	{
		int value = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "UPDATE customer SET username=? WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newU);
			pstmt.setString(2,old);
			value = pstmt.executeUpdate();
			
			if(value!=-1)
			{
				sql = "COMMIT";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername

	//=============================================================================
	
	public int updateFirstName(String newF,String uname)
	{
		int value = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			String sql = "UPDATE customer SET firstname=? WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newF);
			pstmt.setString(2,uname);
			value = pstmt.executeUpdate();
			
			if(value!=-1)
			{
				sql = "COMMIT";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername

	//=============================================================================

	public int updateLastName(String newL,String uname)
	{
		int value = -1;

		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{

			String sql = "UPDATE customer SET lastname=? WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,newL);
			pstmt.setString(2,uname);
			value = pstmt.executeUpdate();

			if(value!=-1)
			{
				sql = "COMMIT";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}//updateUsername
	
	
	
	

	public int removeUserByUsername(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

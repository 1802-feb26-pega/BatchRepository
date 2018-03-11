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



	public User getUserByUsername(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	public int removeUserByUsername(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

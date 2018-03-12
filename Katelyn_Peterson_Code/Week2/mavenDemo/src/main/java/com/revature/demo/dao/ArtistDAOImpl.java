package com.revature.demo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.demo.util.ConnectionFactory;
import com.revature.demo.pojos.Artist;

public class ArtistDAOImpl implements ArtistDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	
	public List<Artist> getAllArtists()
	{
		List<Artist> artists = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM artist";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Artist temp = new Artist();
				
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				
				artists.add(temp);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return artists;
	}

	public Artist getArtistById(int id)
	{
		// Variables
		Artist found = new Artist();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM artist WHERE artistid = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				found.setId(rs.getInt("artistid"));
				found.setName(rs.getString("name"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return found;
	}

	public String getNameById(int id)
	{
		// Variables
		Artist found = getArtistById(id);
		String artName = found.getName();
		
		return artName;
	}

	public void addArtist(Artist newArtist)
	{
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "{CALL add_artist(?)}";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, newArtist.getName());
			cstmt.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void updateArtist(int id, Artist updatedArtist)
	{
		// Variables
		//Artist updated;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "UPDATE artist SET name = ? WHERE artistid = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatedArtist.getName());
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//updated  = getArtistById(id);
		
		//return updated;
	}
	
	public void removeArtist(int id)
	{
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "{CALL remove_artist(?)}";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, id);
			cstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}

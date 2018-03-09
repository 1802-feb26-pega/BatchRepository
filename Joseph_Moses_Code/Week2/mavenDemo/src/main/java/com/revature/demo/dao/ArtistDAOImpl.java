package com.revature.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.demo.pojos.Artist;
import com.revature.demo.util.ConnectionFactory;

public class ArtistDAOImpl implements ArtistDAO{

	public List<Artist> getAllArtists() {

		List<Artist> artists = new ArrayList<Artist>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM artist";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Artist temp = new Artist();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				artists.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artists;
	}

	public Artist getArtistById(int id) {
		Artist output = new Artist();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM artist WHERE artistId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			output.setId(rs.getInt(1));
			output.setName(rs.getString(2));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

	public String getNameById(int id) {
		String output = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT name FROM artist WHERE artistId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			output = rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

	public Artist addArtist(String name) {
		
		Artist output = new Artist();
		int value = 0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT MAX(artistId) FROM artist";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int lastId = rs.getInt(1);
			
			sql = "INSERT INTO artist VALUES(?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++lastId);
			output.setId(lastId);
			pstmt.setString(2, name);
			output.setName(name);
			
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

	public Artist updateArtist(int id, String newName) {
		Artist output = new Artist();
		int value = 0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			String sql = "UPDATE artist SET name = ? WHERE artistId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newName);
			output.setName(newName);
			pstmt.setInt(2, id);
			output.setId(id);
			
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

}
